package net.novatech.jbserver.network.bedrock;

import java.net.InetSocketAddress;

import com.nukkitx.protocol.bedrock.BedrockPong;
import com.nukkitx.protocol.bedrock.BedrockServerEventHandler;
import com.nukkitx.protocol.bedrock.BedrockServerSession;

import net.novatech.jbserver.network.bedrock.handler.BedrockLoginHandler;
import net.novatech.jbserver.server.Server;
import net.novatech.protocol.GameListener;
import net.novatech.protocol.packet.AbstractPacket;

public class BedrockHandler extends GameListener{

	@Override
	public void receivePacket(AbstractPacket packet) {
			
	}

}
