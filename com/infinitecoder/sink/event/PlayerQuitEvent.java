package com.infinitecoder.sink.event;

import com.infinitecoder.sink.entity.Player;

public class PlayerQuitEvent extends Event {
	
	private Player player;
	private String quitMessage;
	
	public PlayerQuitEvent(Player player) {
		this.player = player;
		this.quitMessage = "";
		this.setAsynchronous(true);
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
