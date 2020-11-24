package net.novatech.jbserver.network.java.handler;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.github.steveice10.mc.protocol.ServerLoginHandler;
import com.github.steveice10.packetlib.Session;

import net.novatech.jbserver.network.java.JavaNetworkManager;
import net.novatech.jbserver.network.java.JavaSession;
import net.novatech.jbserver.player.java.JavaPlayer;
import net.novatech.jbserver.player.java.JavaPlayerInfo;
import net.novatech.jbserver.server.Server;

import java.util.*;

public class JavaLoginHandler implements ServerLoginHandler{
	
	private JavaNetworkManager mgr = null;
	
	public JavaLoginHandler(JavaNetworkManager mgr) {
		this.mgr = mgr;
	}
	
	public JavaNetworkManager getManager() { return this.mgr; }

	@Override
	public void loggedIn(Session session) {
		GameProfile profile = session.getFlag(MinecraftConstants.PROFILE_KEY);
		UUID uid = profile.getId();
		JavaSession sesion = new JavaSession(session);
		JavaPlayerInfo info = new JavaPlayerInfo(session.getLocalAddress().toString(),session.getPort(),uid);
		
		Server.getInstance().getFactoryManager().getPlayerFactory().addPlayer(new JavaPlayer(sesion, info));
	}

}
