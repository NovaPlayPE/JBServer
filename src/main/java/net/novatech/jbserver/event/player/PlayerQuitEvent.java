package net.novatech.jbserver.event.player;

import net.novatech.jbserver.player.Player;

import lombok.Getter;
import lombok.Setter;

public class PlayerQuitEvent extends PlayerEvent {

	@Getter
	@Setter
	private String quitMessage;
	
	@Getter
	private String reason;
	
	@Getter
	@Setter
	private boolean autoSave = true;
	
	public PlayerQuitEvent(Player player, String quitMessage) {
		this(player, quitMessage, true);
	}
	
	public PlayerQuitEvent(Player player, String quitMessage, boolean autoSave) {
		this(player, quitMessage, "No reason", autoSave);
	}
	
	public PlayerQuitEvent(Player player, String quitMessage, String reason, boolean autoSave) {
		super(player);
		this.quitMessage = quitMessage;
		this.reason = reason;
		this.autoSave = autoSave;
	}

}
