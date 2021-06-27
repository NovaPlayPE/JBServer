package net.novatech.jbserver.event.player;

import lombok.Getter;
import net.novatech.jbprotocol.packet.AbstractPacket;
import net.novatech.jbserver.player.Player;

public class PlayerPacketReceiveEvent extends PlayerEvent {

	@Getter
	private AbstractPacket packet;
	
	public PlayerPacketReceiveEvent(Player player, AbstractPacket pk) {
		super(player);
		this.packet = pk;
	}

}