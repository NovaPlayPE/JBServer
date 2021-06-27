package net.novatech.jbserver.network.bedrock;

import lombok.Getter;
import lombok.Setter;
import net.novatech.jbprotocol.bedrock.BedrockSession;
import net.novatech.jbprotocol.bedrock.packets.BedrockPacket;
import net.novatech.jbprotocol.packet.AbstractPacket;
import net.novatech.jbserver.event.player.PlayerPacketReceiveEvent;
import net.novatech.jbserver.event.player.PlayerPacketSendEvent;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.bedrock.retranslator.BedrockRetranslatorSector;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.bedrock.BedrockPlayer;

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
	}

}
