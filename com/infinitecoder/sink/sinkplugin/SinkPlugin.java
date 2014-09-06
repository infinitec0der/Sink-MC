package com.infinitecoder.sink.sinkplugin;

import com.infinitecoder.sink.Plugin;
import com.infinitecoder.sink.command.Command;
import com.infinitecoder.sink.entity.Player;

public class SinkPlugin extends Plugin {
	
	@Override
	public void onEnable() {
		this.registerCommand(new Command() {

			@Override
			public String getName() {
				return "help";
			}

			@Override
			public boolean processCommand(Player player, String[] args) {
				if(args.length == 0) {
					
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "";
			}
			
		});
		this.registerCommand(new Command() {

			@Override
			public String getName() {
				return "plugins";
			}

			@Override
			public boolean processCommand(Player player, String[] args) {
				return false;
			}

			@Override
			public String getDescription() {
				return null;
			}
			
		});
	}
	
}
