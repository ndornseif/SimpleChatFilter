package com.neal.simpleChatFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleChatFilter extends JavaPlugin{

	FileConfiguration config = getConfig();

	static List<String> blacklist = new ArrayList<String>();

	 // Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	
    	//AutoKick kicks Players that send unallowed Words
        config.addDefault("AutoKick", true);
        //The Message used for Kicking Players
        config.addDefault("KickMessage", "Please dont use words not allowed on this server!");
        //The Message used for Warning Players
        config.addDefault("WarningMessage", "Please dont use words not allowed on this server!");
        //The Directory used to save the Blacklist .csv file
        config.addDefault("BlacklistCSVPath", Bukkit.getWorldContainer().getAbsolutePath()+"/plugins/SimpleChatFilter/blacklist.csv");
        Bukkit.getLogger().info("[SimpleChatFilter] World Default Blacklist directory: "+Bukkit.getWorldContainer().getAbsolutePath()+"/plugins/SimpleChatFilter/blacklist.csv");
        config.options().copyDefaults(true);
        saveConfig();
        
        getServer().getPluginManager().registerEvents(new MessageListener(this), this);

        try {
			blacklist = CSVparser.main(config.getString("BlacklistCSVPath"));
			Bukkit.getLogger().info("[SimpleChatFilter] Blacklist found");

		} catch (FileNotFoundException e) {
			
			Bukkit.getLogger().warning("[SimpleChatFilter] The Blacklist file was not found:"+config.getString("BlacklistCSVPath"));
			Bukkit.getLogger().warning("[SimpleChatFilter] Disabling plugin to prevent errors");

			//If there is no Blacklist the plugin disables itself
			getServer().getPluginManager().disablePlugin(this);		
			
		}
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
		Bukkit.getLogger().info("[SimpleChatFilter] Plugin was Disabled");

    }
    public static List<String> getBlacklist(){
		return blacklist;
    	
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("reloadchatblacklist")) {
    		if(sender.isOp()){
    		try {
    			blacklist = CSVparser.main(config.getString("BlacklistCSVPath"));
    			sender.sendMessage("Reloading Blacklist:"+config.getString("BlacklistCSVPath"));
    			return true;
    		} catch (FileNotFoundException e) {
    			
    			sender.sendMessage("The Blacklist file was not found:"+config.getString("BlacklistCSVPath"));
    			sender.sendMessage("Disabling plugin to prevent errors.");

    			//If there is no Blacklist the plugin disables itself
    			getServer().getPluginManager().disablePlugin(this);		
    			return false;
    		}
    		}else{
    			sender.sendMessage("This command can only be used by Operators!");
    			return false;
    		}
    	}  
            
    	return false; 
    }
}
