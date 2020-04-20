package net.novaplay.jbserver.network.java.handler;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.github.steveice10.mc.protocol.ServerLoginHandler;
import com.github.steveice10.packetlib.Session;

import java.util.*;

import net.novaplay.jbserver.network.java.JavaNetworkManager;

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
	}

}
