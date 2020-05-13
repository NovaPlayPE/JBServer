package net.novaplay.jbserver.network.bedrock.handler;

import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.packet.LoginPacket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.novaplay.jbserver.network.bedrock.BedrockSession;
import net.novaplay.jbserver.player.bedrock.BedrockPlayer;
import net.novaplay.jbserver.player.bedrock.BedrockPlayerInfo;
import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.utils.bedrock.CertificateData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class JBBedrockLoginPacketHandler implements BedrockPacketHandler {

	private static final Pattern userNamePattern = Pattern.compile("^[aA-zZ\\s\\d_]{3,16}+$");

	@Getter
	private final BedrockSession session;

	@Override
	public boolean handle(LoginPacket packet) {
		CertificateData certificateData = CertificateData.read(packet);

		if (!certificateData.isXboxAuthed() && Server.getInstance().getServerSettings().isOnlineModeEnabled()) {
			session.getServerSession().disconnect("Not authed");
			return true;
		}

		String username = certificateData.getUserName().toLowerCase();
		Matcher matcher = userNamePattern.matcher(username);

		if (!matcher.matches() || username.equalsIgnoreCase("rcon") || username.equalsIgnoreCase("console")) {
			session.getServerSession().disconnect("Invalid username");
			return true;
		}

		if (!certificateData.getSkin().isValid()) {
			session.getServerSession().disconnect("Invalid skin");
			return true;
		}

		BedrockPlayerInfo info = new BedrockPlayerInfo(this.getSession().getServerSession().getAddress().getAddress().toString(),
				this.getSession().getServerSession().getAddress().getPort(),
				certificateData.getClientUUID());

		Server.getInstance().getPlayerManager().addPlayer(new BedrockPlayer(this.getSession(), info));

		return true;
	}
}