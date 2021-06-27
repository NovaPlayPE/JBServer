package net.novatech.jbserver.event.player;

import lombok.Getter;
import net.novatech.jbprotocol.packet.AbstractPacket;
import net.novatech.jbserver.player.Player;

public class PlayerPacketSendEvent extends PlayerEvent{
	
	@Getter
	private AbstractPacket packet;
	
	public PlayerPacketSendEvent(Player player, AbstractPacket pk) {
		super(player);
		this.packet = pk;
	}
	
}