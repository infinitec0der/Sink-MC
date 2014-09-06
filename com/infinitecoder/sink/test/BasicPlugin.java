package com.infinitecoder.sink.test;

import com.infinitecoder.sink.Plugin;
import com.infinitecoder.sink.command.Command;
import com.infinitecoder.sink.entity.Player;
import com.infinitecoder.sink.event.AsyncPlayerChatEvent;
import com.infinitecoder.sink.event.EventHandler;
import com.infinitecoder.sink.event.Listener;
import com.infinitecoder.sink.event.PlayerJoinEvent;
import com.infinitecoder.sink.event.PlayerQuitEvent;
import com.infinitecoder.sink.util.ChatColor;

public class BasicPlugin extends Plugin implements Listener {

	public void onEnable() {
		Command cmd = new Command() {

			@Override
			public boolean processCommand(Player player, String[] args) {
				return false;
			}
			
		};
		this.registerCommand(new Command() {

			@Override
			public String getName() {
				return "yolo";
			}

			@Override
			public boolean processCommand(Player player, String[] args) {
				if(args.length == 1) {
					player.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + getPluginInformation().getName() + ChatColor.GRAY + "] " + ChatColor.WHITE + args[0]);
					return true;
				}
				return false;
			}
			
		});
		this.getServer().getPluginManager().registerEvents(this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(ChatColor.GREEN + event.getPlayer().getName() + " has joined the Sink server!");
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setFormat(ChatColor.DARK_GRAY + event.getPlayer().getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + ChatColor.ITALIC + event.getMessage());
		if(event.getMessage().equalsIgnoreCase("Hello")) {
			event.getPlayer().sendMessage("Hi!");
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage(ChatColor.RED + event.getPlayer().getName() + " has left the Sink server!");
	}
	
}
