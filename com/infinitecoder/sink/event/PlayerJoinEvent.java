package com.infinitecoder.sink.event;

import com.infinitecoder.sink.entity.Player;

public class PlayerJoinEvent extends Event {
	
	private Player player;
	private String joinMessage;
	
	public PlayerJoinEvent(Player player) {
		this.player = player;
		this.joinMessage = "";
		this.setAsynchronous(true);
	}

	public String getJoinMessage() {
		return joinMessage;
	}

	public void setJoinMessage(String joinMessage) {
		this.joinMessage = joinMessage;
	}

	public Player getPlayer() {
		return player;
	}
	
}
