package me.tatteaid.commands.user;

import me.tatteaid.AutoAnnouncer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleCommand implements CommandExecutor {

    private final AutoAnnouncer instance;

    public ToggleCommand(AutoAnnouncer instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }
}