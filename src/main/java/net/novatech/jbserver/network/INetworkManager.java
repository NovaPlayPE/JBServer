package net.novatech.jbserver.network;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public interface INetworkManager {
	
	void start();
	void stop();
	int getPort();
}
