package net.iVirus5.listeners;

import net.iVirus5.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnMoveUpdate implements Listener{

	@EventHandler
	public void onMove(PlayerMoveEvent e){
		if(Main.frozen.contains(e.getPlayer().getName())){
			e.getPlayer().teleport(e.getPlayer().getLocation());
		}
	}
}
