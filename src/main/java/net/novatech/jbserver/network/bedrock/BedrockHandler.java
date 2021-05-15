package net.novatech.jbserver.network.bedrock;

import java.net.InetSocketAddress;

import com.nukkitx.protocol.bedrock.BedrockPong;
import com.nukkitx.protocol.bedrock.BedrockServerEventHandler;
import com.nukkitx.protocol.bedrock.BedrockServerSession;

import net.novatech.jbprotocol.listener.GameListener;
import net.novatech.jbprotocol.packet.AbstractPacket;
import net.novatech.jbserver.network.bedrock.handler.BedrockLoginHandler;
import net.novatech.jbserver.server.Server;

public class BedrockHandler extends GameListener{

	@Override
	public void receivePacket(AbstractPacket packet) {
			
	}

}
