package com.infinitecoder.sink.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import com.infinitecoder.sink.Sink;
import com.infinitecoder.sink.entity.Player;
import com.infinitecoder.sink.util.ChatColor;

public class PluginCommand extends CommandBase {
	
	private Command command;

	public PluginCommand(Command command) {
		this.command = command;
	}

    public String getCommandName() {
        return command.getName();
    }

    public String getCommandUsage(ICommandSender commandSender) {
        return "/" + command.getName();
    }

    public void processCommand(ICommandSender commandSender, String[] args) {
        EntityPlayerMP entityPlayer = args.length == 0 ? getCommandSenderAsPlayer(commandSender) : getPlayer(commandSender, args[0]);
        Player player = Sink.getServer().getPlayer(entityPlayer);
        if(!command.processCommand(player, args)) {
        	player.sendMessage(new ChatComponentText(ChatColor.RED + "Unknown command. Type /help for a list of commands."));
        }
    }
    
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
    {
        return false;
    }

}