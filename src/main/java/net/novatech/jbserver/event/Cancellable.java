package net.novatech.jbserver.event;

public interface Cancellable {

    boolean isCancelled();
    void setCancelled(boolean forceCancel);
    void setCancelled();
}