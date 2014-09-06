package com.infinitecoder.sink;

import java.util.ArrayList;

import org.apache.logging.log4j.Logger;

import com.infinitecoder.sink.command.Command;

public class Plugin {
	
	private PluginInformation pluginInfo;
	private ArrayList<Command> registeredCommands = new ArrayList<Command>();
	
	public Plugin(PluginInformation pluginInfo) {
		this.pluginInfo = pluginInfo;
	}
	
	public Plugin() { }

	public void onEnable() { }
	
	public void onDisable() { }
	
	public void registerCommand(Command command) {
		registeredCommands.add(command);
	}
	
	public Logger getLogger() {
		return Sink.getLogger();
	}
	
	public Server getServer() {
		return Sink.getServer();
	}
	
	public boolean isEnabled() {
		return getServer().isPluginEnabled(this);
	}
	
	public PluginInformation getPluginInformation() {
		return pluginInfo;
	}
	
	protected synchronized void loadPluginInformation(PluginInformation pluginInfo) {
		this.pluginInfo = pluginInfo;
	}

	public ArrayList<Command> getRegisteredCommands() {
		return registeredCommands;
	}
	
}
