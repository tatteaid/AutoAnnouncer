package me.tatteaid.commands;

import me.tatteaid.AutoAnnouncer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnnouncerCommand implements CommandExecutor {

    private final AutoAnnouncer instance;

    public AnnouncerCommand(AutoAnnouncer instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (!player.hasPermission("announcer.cmd")) {
            player.sendMessage(ChatColor.RED + "You do not have permission for this command.");
        }

        if(args.length == 0) {
            player.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
            player.sendMessage(ChatColor.BLUE.toString() + ChatColor.BOLD + "Commands:");
            player.sendMessage(ChatColor.AQUA + "/announcer help");
            player.sendMessage(ChatColor.AQUA + "/announcer version");
            player.sendMessage(ChatColor.AQUA + "/announcer author");
            player.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
            return true;
        }

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("help")) {
                player.sendMessage("This is a help message.");
                return true;
            }

            if(args[0].equalsIgnoreCase("version")) {
                player.sendMessage("This is a version message.");
                return true;
            }

            if(args[0].equalsIgnoreCase("author")) {
                player.sendMessage("This is an author message.");
                return true;
            }
        }

        player.sendMessage(ChatColor.RED + "Usage: /announcer [help/version/author]");
        return true;
    }
}