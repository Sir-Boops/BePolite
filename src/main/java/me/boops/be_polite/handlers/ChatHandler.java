package me.boops.be_polite.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import me.boops.be_polite.config.Config;

public class ChatHandler {
	
	Config conf = new Config();
	
	public ChatHandler(AsyncPlayerChatEvent event){
		
		Player player = event.getPlayer();
		String message = event.getMessage();
		JSONArray bad_words = new Config().loadConfig("config").getJSONArray("bad_words");
		JSONObject swear_jar = new Config().loadConfig("swear_jar");
		
		//Check if the use said a blacklisted word
		for(int i=0; bad_words.length()>i; i++){
			
			if(message.toLowerCase().replace(" ", "").contains(bad_words.getString(i).toLowerCase())){
				event.setCancelled(true);
				player.sendMessage("Bad Player!, This has been logged!");
				
				//Add it to the players record
				if(swear_jar.has(player.getUniqueId().toString())){
					
					//Add to the record
					JSONArray words_rec = swear_jar.getJSONArray(player.getUniqueId().toString());
					words_rec.put(bad_words.getString(i).toLowerCase());
					swear_jar.put(player.getUniqueId().toString(), words_rec);
					
					//Save the config
					new Config().saveConfig("swear_jar", swear_jar);
					
				} else {
					
					//Create their record
					JSONArray words_rec = new JSONArray();
					
					words_rec.put(bad_words.getString(i).toLowerCase());
					swear_jar.put(player.getUniqueId().toString(), words_rec);
					
					//Save the config
					new Config().saveConfig("swear_jar", swear_jar);
					
				}
			}
			
		}
		
	}
}
