package net.novatech.jbserver.event.player;

import net.novatech.jbserver.event.Event;
import net.novatech.jbserver.player.Player;

public abstract class PlayerEvent extends Event {

    private final Player player;

    public PlayerEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}