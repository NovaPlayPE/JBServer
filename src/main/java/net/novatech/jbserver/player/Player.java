package net.novatech.jbserver.player;

import net.novatech.jbserver.command.CommandSender;
import net.novatech.jbserver.entity.EntityType;
import net.novatech.jbserver.entity.IEntity;
import net.novatech.jbserver.entity.human.EntityHumanoid;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.protocol.impl.JBTextPacket;

public abstract class Player extends EntityHumanoid implements CommandSender, IEntity{
	
	public Player() {
		super(EntityType.HUMAN);
	}
	
	public abstract NetworkSession getSession();
	public abstract PlayerInfo getPlayerInfo();
	
	@Override
	public void sendMessage(String message) {
		JBTextPacket textPacket = new JBTextPacket();
		textPacket.content = message;
		
		getSession().sendPacket(textPacket);
	}

	@Override
	public void setOp(boolean value) {
		
		
	}

	@Override
	public boolean isOp() {
		return false;
	}
}
