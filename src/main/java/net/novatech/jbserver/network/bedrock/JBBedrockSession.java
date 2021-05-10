package net.novatech.jbserver.network.bedrock;

import lombok.Getter;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.bedrock.retranslator.BedrockRetranslatorSector;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.protocol.bedrock.BedrockSession;

public class JBBedrockSession implements NetworkSession {

	public JBBedrockSession(BedrockSession session) {
		this.serverSession = session;
	}
	
	@Getter
	private BedrockSession serverSession = null;
	
	@Override
	public void sendPacket(JBPacket packet) {
		this.serverSession.sendPacket(BedrockRetranslatorSector.translateTo(packet));
	}

}
