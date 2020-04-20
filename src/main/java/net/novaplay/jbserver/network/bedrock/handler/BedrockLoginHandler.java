package net.novaplay.jbserver.network.bedrock.handler;

import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.packet.LoginPacket;

public class BedrockLoginHandler implements BedrockPacketHandler {
	
	@Override
	public boolean handle(LoginPacket pk) { 
		
		return true;
	}
	
}