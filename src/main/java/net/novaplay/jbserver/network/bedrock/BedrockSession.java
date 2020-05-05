package net.novaplay.jbserver.network.bedrock;

import com.nukkitx.protocol.bedrock.BedrockServerSession;
import net.novaplay.jbserver.network.NetworkSession;
import net.novaplay.jbserver.network.bedrock.retranslator.BedrockRetranslatorSector;
import net.novaplay.jbserver.network.protocol.JBPacket;

import lombok.Getter;

public class BedrockSession implements NetworkSession {

	public BedrockSession(BedrockServerSession session) {
		this.serverSession = session;
	}
	
	@Getter
	private BedrockServerSession serverSession = null;
	
	@Override
	public void sendPacket(JBPacket packet) {
		this.serverSession.sendPacket(BedrockRetranslatorSector.translateTo(packet));
	}

}
