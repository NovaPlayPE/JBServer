package net.novatech.jbserver.player;

import net.novatech.jbserver.command.CommandSender;
import net.novatech.jbserver.entity.EntityHumanoid;
import net.novatech.jbserver.entity.EntityType;
import net.novatech.jbserver.entity.IEntity;
import net.novatech.jbserver.network.NetworkSession;

public abstract class Player extends EntityHumanoid implements CommandSender, IEntity{
	
	public Player() {
		super(EntityType.HUMAN);
	}
	
	public abstract NetworkSession getSession();
	public abstract PlayerInfo getPlayerInfo();
	
	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOp(boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOp() {
		// TODO Auto-generated method stub
		return false;
	}
}
