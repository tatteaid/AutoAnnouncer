package me.tatteaid.utils.command;

import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public abstract class SubCommand {

    private String name;
    private String description;
    private String permission;
    private boolean playerOnly;

    protected SubCommand(String name, String description, String permission, boolean playerOnly) {
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.playerOnly = playerOnly;
    }

    public abstract void executeSubCommand(Player player, String args[]);
}