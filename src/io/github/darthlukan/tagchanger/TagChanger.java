package io.github.darthlukan.tagchanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class TagChanger extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("TagChanger has been enabled! :)");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("TagChanger has been disabled! :(");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("changetag")) {
			return changeTabListTag(sender, cmd, args);
		}
		// Should never get here.
		return false;
	}
	
	public boolean changeTabListTag(CommandSender sender, Command cmd, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("ChangeTag is not runnable from the console.  Please login as a player to use.");
		} else {
			Player player = (Player) sender;
			
			if (!player.hasPermission("name.change")) {
				sender.sendMessage("You do not have name.change permissions on this server!");
				return true;
			}
			// Append their status to their name tag
			String currentName = player.getName();
			String formatName = "%s: %s";
			
			StringBuilder builder = new StringBuilder();
			
			for (String string : args) {
				if (builder.length() > 0) {
					builder.append(" ");
				}
				builder.append(string);
			}
			
			String newName = String.format(formatName, currentName, builder.toString());
			
			player.setPlayerListName(newName);
		}
		return true;
	}
}
