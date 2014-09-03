package com.infinitecoder.minebukkit.event;

import com.infinitecoder.minebukkit.entity.Player;

public class PlayerChatEvent extends CancellableEvent {
	
	private Player player;
	private String message;
	private String format;
	
	public PlayerChatEvent(Player player, String message) {
		this.player = player;
		this.message = message;
		this.format = player.getName() + ": " + message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Player getPlayer() {
		return player;
	}

}
