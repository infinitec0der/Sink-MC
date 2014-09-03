package com.infinitecoder.minebukkit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MineBukkit {
	
	private static MineBukkit instance;
	private static Server server;
	
	public MineBukkit() {
		instance = this;
		server = new Server();
	}
	
	public static MineBukkit getMineBukkit() {
		if(instance == null) {
			System.err.println("MINEBUKKIT INSTANCE == NULL");
		}
		return instance;
	}
	
	public static Server getServer() {
		if(server == null) {
			System.err.println("SERVER == NULL");
		}
		return server;
	}
	
	public static Logger getLogger() {
		return LogManager.getLogger();
	}
	
	public void start() {
		getServer().start();
	}
	
	public void stop() {
		getServer().stop();
	}
	
}
