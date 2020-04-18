package net.novaplay.jbserver.network.bedrock.retranslator.impl;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.LoginPacket;

import net.novaplay.jbserver.network.bedrock.retranslator.BedrockRetranslator;
import net.novaplay.jbserver.network.protocol.JBPacket;

public class LoginRetranslator extends BedrockRetranslator {

	@Override
	public JBPacket translateFrom(BedrockPacket pk) {
		LoginPacket p = (LoginPacket)pk;
		return null;
	}

	@Override
	public BedrockPacket translateTo(JBPacket pk) {
		// TODO Auto-generated method stub
		return null;
	}

}
