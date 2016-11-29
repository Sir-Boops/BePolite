package me.boops.be_polite;

import org.bukkit.plugin.java.JavaPlugin;

import me.boops.be_polite.config.Config;
import me.boops.be_polite.listeners.ChatListener;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	Config conf = new Config();
	public static String VERSION = "0.0.1";
	
	@Override
	public void onEnable(){
		
		//Public plugin
		plugin = this;
		
		//Register listeners
		this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
		
		//Instal load of the config
		conf.create();
		
	}

}
