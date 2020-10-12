package net.novatech.jbserver.network.bedrock;

import com.nukkitx.protocol.bedrock.BedrockServerSession;

import lombok.Getter;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.bedrock.retranslator.BedrockRetranslatorSector;
import net.novatech.jbserver.network.protocol.JBPacket;

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
