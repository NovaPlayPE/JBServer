package net.novaplay.jbserver.event.player;

import net.novaplay.jbserver.event.Event;
import net.novaplay.jbserver.player.Player;

public abstract class PlayerEvent extends Event {

    private final Player player;

    public PlayerEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}