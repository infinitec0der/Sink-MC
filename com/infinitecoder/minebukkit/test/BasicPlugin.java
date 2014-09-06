package com.infinitecoder.minebukkit.test;

import com.infinitecoder.minebukkit.Plugin;
import com.infinitecoder.minebukkit.event.EventHandler;
import com.infinitecoder.minebukkit.event.Listener;
import com.infinitecoder.minebukkit.event.PlayerChatEvent;
import com.infinitecoder.minebukkit.event.PlayerJoinEvent;
import com.infinitecoder.minebukkit.event.PlayerQuitEvent;
import com.infinitecoder.minebukkit.util.ChatColor;

public class BasicPlugin extends Plugin implements Listener {

	public BasicPlugin() {
		super("TestPlugin", "1.0", "infinitec0der");
		this.getServer().getPluginManager().registerEvents(this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(ChatColor.GREEN + event.getPlayer().getName() + " has joined the MineBukkit server!");
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		event.setFormat(ChatColor.DARK_GRAY + event.getPlayer().getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + ChatColor.ITALIC + event.getMessage());
		if(event.getMessage().equalsIgnoreCase("Hello")) {
			event.getPlayer().sendMessage("Hi!");
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage(ChatColor.RED + event.getPlayer().getName() + " has left the MineBukkit server!");
	}
	
}
