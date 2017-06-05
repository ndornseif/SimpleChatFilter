package com.neal.simpleChatFilter;

import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageChecker {

	public static boolean isMessageAllowed(AsyncPlayerChatEvent pMessage){
		for (int x=0; x<SimpleChatFilter.getBlacklist().size(); x++){
			if(pMessage.getMessage().contains(SimpleChatFilter.getBlacklist().get(x))){
				return false;
			}
		}
		return true;
	}
	
}
