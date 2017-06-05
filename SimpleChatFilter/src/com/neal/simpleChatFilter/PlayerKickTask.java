package com.neal.simpleChatFilter;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerKickTask extends BukkitRunnable {

	private Player playerToKick;
	private JavaPlugin plugin;

    public PlayerKickTask(JavaPlugin plugin,Player pPlayer) {
        this.playerToKick = pPlayer;
        this.plugin = plugin;
    }

    @Override
    public void run() {
    	playerToKick.kickPlayer(plugin.getConfig().getString("KickMessage"));
    }

}