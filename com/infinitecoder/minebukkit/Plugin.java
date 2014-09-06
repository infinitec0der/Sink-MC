package com.infinitecoder.minebukkit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

public class Plugin {
	
	private boolean isEnabled;
	private String name;
	private String version;
	private String[] authors;
	
	public Plugin(String name, String version, String[] authors) {
		this.name = name;
		this.version = version;
		this.authors = authors;
		this.isEnabled = false;
	}
	
	public Plugin(String name, String version, String author) {
		this.name = name;
		this.version = version;
		this.authors = new String[]{author};
	}
	
	public void onEnable() {
		getLogger().info(name + " version " + version + " by " + authors[0] + " has been enabled.");
		isEnabled = true;
	}
	
	public void onDisable() {
		getLogger().info(name + " version " + version + " by " + authors[0] + " has been disabled.");
		isEnabled = false;
	}
	
	public Logger getLogger() {
		return MineBukkit.getLogger();
	}
	
	public Server getServer() {
		return MineBukkit.getServer();
	}
	
}
