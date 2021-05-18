package net.novatech.jbserver.player;

import net.novatech.jbserver.command.CommandSender;
import net.novatech.jbserver.entity.EntityType;
import net.novatech.jbserver.entity.IEntity;
import net.novatech.jbserver.entity.human.EntityHumanoid;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.protocol.impl.JBChatSendPacket;
import net.novatech.jbserver.server.Server;

public abstract class Player extends EntityHumanoid implements CommandSender, IEntity{
	
	public Player() {
		super(EntityType.HUMAN);
	}
	
	public abstract NetworkSession getSession();
	public abstract PlayerInfo getPlayerInfo();
	
	public Server getServer() {
		return Server.getInstance();
	}
	
	@Override
	public void sendMessage(String message) {
		JBChatSendPacket textPacket = new JBChatSendPacket();
		textPacket.content = message;
		textPacket.playerInfo = getPlayerInfo();
		
		getSession().sendPacket(textPacket);
	}
	
	public void close(String cause) {
		this.close();
	}

	@Override
	public void setOp(boolean value) {
		
		
	}

	@Override
	public boolean isOp() {
		return false;
	}
}
