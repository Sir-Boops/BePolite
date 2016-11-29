package me.boops.be_polite.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import me.boops.be_polite.Main;

public class Config {

	public JSONObject config;
	public JSONObject SwearJar;
	public void create() {

		// Check if the folder exists
		if (!Main.plugin.getDataFolder().exists()) {

			// Make the directory
			Main.plugin.getDataFolder().mkdir();

			// Create the json files
			JSONObject config = new JSONObject();
			JSONObject swear_jar = new JSONObject();

			// Add the base args
			config.put("version", Main.VERSION);
			swear_jar.put("version", Main.VERSION);
			config.put("bad_words", new JSONArray());

			// Write the new file
			try {
				FileWriter conf = new FileWriter(Main.plugin.getDataFolder() + "/config.json");
				FileWriter swearjar = new FileWriter(Main.plugin.getDataFolder() + "/swear_jar.json");
				conf.write(config.toString());
				swearjar.write(swear_jar.toString());
				conf.flush();
				swearjar.flush();
				conf.close();
				swearjar.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public JSONObject loadConfig(String name) {
		
		StringBuilder sb = new StringBuilder();

		try {

			// Read The Config File
			BufferedReader br = new BufferedReader(new FileReader(Main.plugin.getDataFolder() + "/" + name + ".json"));
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();

		} catch (IOException err) {
			err.printStackTrace();
		}
		JSONObject config = new JSONObject(sb.toString());
		return config;
	}
	
	public void saveConfig(String name, JSONObject json) {
		
		// Write the new file
		try {
			FileWriter swearjar = new FileWriter(Main.plugin.getDataFolder() + "/" + name + ".json");
			swearjar.write(json.toString());
			swearjar.flush();
			swearjar.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
