package com.infinitecoder.sink;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import com.infinitecoder.sink.command.Command;
import com.infinitecoder.sink.entity.Player;
import com.infinitecoder.sink.sinkplugin.SinkPlugin;
import com.infinitecoder.sink.util.ChatColor;

public class Server {
	private static HashMap<Plugin, Boolean> totalPlugins = new HashMap<Plugin, Boolean>();
	private static List<Player> onlinePlayers = new ArrayList<Player>();
	private PluginManager pluginManager;
	private ServerCommandManager commandManager;
	
	public void start(ServerCommandManager commandManager) {
		this.commandManager = commandManager;
		this.pluginManager = new PluginManager();
		loadPlugins();
		Sink.getLogger().info("Server has started with " + (totalPlugins.size() - 1) + " plugin(s) loaded.");
	}
	
	private void loadPlugins() {
		
		//TODO: Fix this somehow..?
		
		SinkPlugin sink = new SinkPlugin();
		totalPlugins.put(sink, true);
		sink.onEnable();
		
		
		File pluginsDir = new File("plugins/");
		if(!pluginsDir.exists()) pluginsDir.mkdirs();
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(".jar");
		    }
		};
		for(File pluginJar : pluginsDir.listFiles(filter)) {
			try {
				URL url = new URL("jar:file:" + pluginJar.getAbsolutePath() + "!/plugin.yml");
				InputStream is = url.openStream();
				Properties properties = new Properties();
				properties.load(is);
				String main = properties.getProperty("main");
				String name = properties.getProperty("name");
				String version = properties.getProperty("version");
				String author = properties.getProperty("author");
				PluginInformation pluginInfo = new PluginInformation(name, version, author);
				URL url2 = pluginJar.toURI().toURL();
				URL[] urls = new URL[] { url2 };
				@SuppressWarnings("resource") ClassLoader cl = new URLClassLoader(urls);
				Class clazz = cl.loadClass(main);
				Plugin plugin = (Plugin)clazz.newInstance();
				plugin.loadPluginInformation(pluginInfo);
				totalPlugins.put(plugin, true);
				plugin.getLogger().info(pluginInfo.getName() + " version " + pluginInfo.getVersion() + " by " + pluginInfo.getPrimaryAuthor() + " has been enabled.");
				plugin.onEnable();
				/*for(Command cmd : plugin.getRegisteredCommands()) {
					commandManager.registerCommand(new PluginCommand(cmd));
				}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		for(Plugin plugin : totalPlugins.keySet()) {
			PluginInformation pluginInfo = plugin.getPluginInformation();
			plugin.getLogger().info(pluginInfo.getName() + " version " + pluginInfo.getVersion() + " by " + pluginInfo.getPrimaryAuthor() + " has been disabled.");
			plugin.onDisable();
		}
	}
	
	public boolean isPluginEnabled(Plugin plugin) {
		return totalPlugins.get(plugin);
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
		return Sink.getServer();
	}
	
	public List<Player> getOnlinePlayers() {
		return onlinePlayers;
	}
	
	public PluginManager getPluginManager() {
		return pluginManager;
	}
	
	public Player getPlayer(EntityPlayerMP nmsPlayer) {
		for(Player player : getOnlinePlayers()) {
			if(player.getNMSEntity() == nmsPlayer)
				return player;
		}
		return null;
	}
	
	public Player getPlayer(String name) {
		for(Player player : getOnlinePlayers()) {
			if(player.getName().equals(name))
				return player;
		}
		return null;
	}
	
	public Set<Plugin> getPlugins() {
		return totalPlugins.keySet();
	}
	
	public ArrayList<Command> getCommands() {
		ArrayList<Command> commands = new ArrayList<Command>();
		for(Plugin p : getPlugins()) {
			for(Command c : p.getRegisteredCommands()) {
				commands.add(c);
			}
		}
		return commands;
	}
	
	public Command getBestCommand(String command) {
		if(command.contains(":")) {
			String pluginName = command.split(":")[0];
			String commandName = command.split(":")[1];
			for(Plugin p : getPlugins()) {
				if(p.getPluginInformation().getName().equalsIgnoreCase(pluginName)) {
					for(Command c : p.getRegisteredCommands()) {
						if(c.getName().equals(commandName)) {
							return c;
						}
					}
				}
			}
		} else {
			for(Plugin p : getPlugins()) {
				for(Command c : p.getRegisteredCommands()) {
					if(c.getName().equals(command)) {
						return c;
					}
				}
			}
		}
		return null;
	}

	public void handleCommand(Player sender, String s) {
		Command cmd = null;
		String[] arguments = null;
		String full = s.substring(1).trim();
		if(full.contains(" ")) {
			String[] temp = full.split(" ");
			String[] args = new String[temp.length - 1];
			for(int i=1;i<temp.length;i++) {
				args[i - 1] = temp[i];
			}
			
			cmd = getBestCommand(full.split(" ")[0]);
			arguments = args;
		} else {
			cmd = getBestCommand(full);
			arguments = new String[]{};
		}
		if(cmd == null) {
			sender.sendMessage(new ChatComponentText(ChatColor.RED + "Unknown command. Type /help for a list of commands."));
			return;
		} else if(!cmd.processCommand(sender, arguments)) {
			sender.sendMessage(new ChatComponentText(ChatColor.RED + "Unknown command. Type /help for a list of commands."));
		}
	}
	
}
