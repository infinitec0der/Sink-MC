package com.infinitecoder.minebukkit.event;

import com.infinitecoder.minebukkit.entity.Player;

public class PlayerQuitEvent extends Event {
	
	private Player player;
	private String quitMessage;
	
	public PlayerQuitEvent(Player player) {
		this.player = player;
		this.quitMessage = "";
	}

	public String getQuitMessage() {
		return quitMessage;
	}

	public void setQuitMessage(String quitMessage) {
		this.quitMessage = quitMessage;
	}

	public Player getPlayer() {
		return player;
	}
	
}
