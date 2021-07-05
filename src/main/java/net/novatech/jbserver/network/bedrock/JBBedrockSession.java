package net.novatech.jbserver.network.bedrock;

import lombok.Getter;
import lombok.Setter;
import net.novatech.jbprotocol.bedrock.BedrockSession;
import net.novatech.jbprotocol.bedrock.packets.BedrockPacket;
import net.novatech.jbprotocol.bedrock.packets.MovePlayerPacket;
import net.novatech.jbprotocol.packet.AbstractPacket;
import net.novatech.jbserver.event.player.PlayerPacketReceiveEvent;
import net.novatech.jbserver.event.player.PlayerPacketSendEvent;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.bedrock.retranslator.BedrockRetranslatorSector;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.bedrock.BedrockPlayer;
import net.novatech.library.math.Vector3d;

public class JBBedrockSession implements NetworkSession {
	
	public JBBedrockSession(BedrockSession session) {
		this.serverSession = session;
	}
	@Getter
	private BedrockPlayer player = null;
	
	public void setPlayer(Player player) {
		this.player = (BedrockPlayer) player;
	}
	
	@Getter
	private BedrockSession serverSession = null;
	
	@Override
	public void sendPacket(JBPacket packet) {
		AbstractPacket pk = BedrockRetranslatorSector.translate(packet);
		this.serverSession.sendPacket(pk);
		
		PlayerPacketSendEvent event = new PlayerPacketSendEvent(getPlayer(), pk);
		event.call();
	}
	
	@Override
	public void handleServerPacket(AbstractPacket packet) {
		if(!(packet instanceof BedrockPacket)) {
			return;
		}
		
		PlayerPacketReceiveEvent event = new PlayerPacketReceiveEvent(getPlayer(), packet);
		event.call();
		
		if(packet instanceof MovePlayerPacket) {
			MovePlayerPacket pk = (MovePlayerPacket)packet;
			Vector3d pos = pk.position.asDouble().clone();
			
			if(pos.distanceSquared(getPlayer().getLocation().getVector()) < 0.01 
					&& pk.rotation.getYaw() % 360 == getPlayer().getLocation().getYaw()
					&& pk.rotation.getPitch() % 360 == getPlayer().getLocation().getPitch()) {
				return;
			}
			if(pos.distanceSquared(getPlayer().getLocation().getVector()) > 100) {
				//sendPosition
				return;
			}
			boolean revert = false;
			
		}
	}

}
