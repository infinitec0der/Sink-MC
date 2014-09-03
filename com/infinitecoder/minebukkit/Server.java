package com.infinitecoder.minebukkit;

import java.util.ArrayList;
import java.util.List;

import com.infinitecoder.minebukkit.entity.Player;
import com.infinitecoder.minebukkit.event.Listener;
import com.infinitecoder.minebukkit.test.BasicPlugin;

public class Server {
	private static List<Plugin> totalPlugins = new ArrayList<Plugin>();
	private static List<Player> onlinePlayers = new ArrayList<Player>();
	private PluginManager pluginManager;
	
	public void start() {
		pluginManager = new PluginManager();
		loadPlugins();
		MineBukkit.getLogger().info("Server has started with " + totalPlugins.size() + " plugin(s) loaded.");
	}
	
	private void loadPlugins() {
		totalPlugins.add(new BasicPlugin());
		
		for(Plugin plugin : totalPlugins) {
			plugin.onEnable();
		}
	}
	
	public void stop() {
		for(Plugin plugin : totalPlugins) {
			plugin.onDisable();
		}
	}
	
	public void playerJoin(Player player) {
		onlinePlayers.add(player);
	}
	
	public void playerQuit(Player player) {
		onlinePlayers.remove(player);
	}
	
	public void broadcast(String message) {
		for(Player player : getOnlinePlayers())
			player.sendMessage(message);
	}
	
	public static Server getServer() {
		return MineBukkit.getServer();
	}
	
	public List<Player> getOnlinePlayers() {
		return onlinePlayers;
	}
	
	public PluginManager getPluginManager() {
		return pluginManager;
	}
	
	public Player getPlayer(String name) {
		for(Player player : getOnlinePlayers()) {
			if(player.getName().equals(name))
				return player;
		}
		return null;
	}
	
}
