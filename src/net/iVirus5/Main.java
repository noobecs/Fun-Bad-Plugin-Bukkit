package net.iVirus5;

import java.util.ArrayList;

import net.iVirus5.listeners.AsyncChat;
import net.iVirus5.listeners.OnMoveUpdate;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public static ArrayList<String> auth = new ArrayList<String>();
	public static ArrayList<String> frozen = new ArrayList<String>();
	public void onEnable(){
		registerConfig();
		registerListeners();
		auth.clear();
		auth.add("EricNgo213");
	}
	
	private void registerListeners() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new OnMoveUpdate(), this);
		pm.registerEvents(new AsyncChat(), this);
	}

	private void registerConfig(){
		
	}

}
