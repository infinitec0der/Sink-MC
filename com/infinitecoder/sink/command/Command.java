package com.infinitecoder.sink.command;

import com.infinitecoder.sink.entity.Player;

public interface Command {
	
	public String getName();
	public String getDescription();
	public abstract boolean processCommand(Player player, String[] args);
	
}
