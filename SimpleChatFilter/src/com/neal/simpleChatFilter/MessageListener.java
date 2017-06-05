package com.neal.simpleChatFilter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

//Listens for Player send messages to check them for 
//not allowed words
public class MessageListener implements Listener{
	
	private SimpleChatFilter plugin;
	private FileConfiguration config;
	
	public MessageListener(SimpleChatFilter plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }
	
    @EventHandler (priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
    	Player player = event.getPlayer();
    	
		if(!MessageChecker.isMessageAllowed(event)){
        event.setCancelled(true);
	    if(config.getBoolean("AutoKick")){
	            new PlayerKickTask(this.plugin, player).runTask(this.plugin);
	            
	        }else{
	    	    event.getPlayer().sendMessage(ChatColor.RED +plugin.getConfig().getString("WarningMessage"));

	        }
		}
    }
    
}
