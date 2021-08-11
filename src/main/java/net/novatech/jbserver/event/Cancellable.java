package net.novatech.jbserver.event;

public interface Cancellable {

	boolean isCancelled();

	void setCancelled();

	void setCancelled(boolean forceCancel);

}