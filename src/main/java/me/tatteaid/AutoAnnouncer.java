package me.tatteaid;

import lombok.Getter;
import me.tatteaid.commands.AnnouncerCommand;
import me.tatteaid.utils.config.ConfigUpdater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.logging.Level;

@Getter
public class AutoAnnouncer extends JavaPlugin {

    private BukkitTask announcementTask;
    private ConfigUpdater configUpdater;

    public static final double CONFIG_VERSION = 1.0;

    @Override
    public void onEnable() {
        //announcementTask = new AnnouncementTask().runTaskTimer(this, 120L, 120L);

        // doesnt add new content to the config unless the file is deleted
        saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        System.out.println("Actual Config Version: " + CONFIG_VERSION);
        System.out.println("Config Version: " + this.getConfig().getDouble("CONFIG_VERSION"));
        System.out.println("Config Values False: " + getConfig().getValues(false));
        System.out.println("Config Values True: " + getConfig().getValues(true));

        configUpdater = new ConfigUpdater(this);
        configUpdater.updateConfig();

        registerCommands();

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) log(Level.INFO, "It is recommend that you use PlaceholderAPI");
    }

    @Override
    public void onDisable() {
        //announcementTask.cancel();
    }

    private void registerCommands() {
        this.getCommand("announcer").setExecutor(new AnnouncerCommand());
    }

    public static void log(Level level, String message) {
        Bukkit.getLogger().log(level, "[AutoAnnouncer] " + message);
    }
}