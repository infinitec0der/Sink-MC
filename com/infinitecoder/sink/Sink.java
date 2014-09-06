package com.infinitecoder.sink;

import net.minecraft.command.ServerCommandManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sink {
	
	private static Sink instance;
	private static Server server;
	
	public Sink() {
		instance = this;
		server = new Server();
	}
	
	public static Sink getSink() {
		return instance;
	}
	
	public static Server getServer() {
		return server;
	}
	
	public static Logger getLogger() {
		return LogManager.getLogger();
	}
	
	public void start(ServerCommandManager commandManager) {
		getServer().start(commandManager);
	}
	
	public void stop() {
		getServer().stop();
	}
	
}
