package net.novaplay.jbserver.network.bedrock;

import com.nukkitx.protocol.bedrock.BedrockServerSession;
import lombok.RequiredArgsConstructor;
import net.novaplay.jbserver.network.NetworkSession;
import net.novaplay.jbserver.network.bedrock.retranslator.BedrockRetranslatorSector;
import net.novaplay.jbserver.network.protocol.JBPacket;

import lombok.Getter;

@RequiredArgsConstructor
public class BedrockSession implements NetworkSession {

	@Getter
	private final BedrockServerSession serverSession;

	@Override
	public void sendPacket(JBPacket packet) {
		this.serverSession.sendPacket(BedrockRetranslatorSector.translateTo(packet));
	}
}