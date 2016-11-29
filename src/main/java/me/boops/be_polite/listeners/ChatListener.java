package me.boops.be_polite.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.boops.be_polite.handlers.ChatHandler;

public class ChatListener implements Listener {
	
	@EventHandler
	public void onTalk(AsyncPlayerChatEvent event){
		
		if(event.getPlayer() instanceof Player){
			
			new ChatHandler(event);
			
		}
	}
}
