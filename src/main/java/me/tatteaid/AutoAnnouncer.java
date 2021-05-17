package me.tatteaid;

import lombok.Getter;
import me.tatteaid.announcements.AnnouncementManager;
import me.tatteaid.commands.AnnouncerCommand;
import me.tatteaid.utils.config.ConfigUpdater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.logging.Level;

@Getter
public class AutoAnnouncer extends JavaPlugin {

    private AnnouncementManager announcementManager;

    private BukkitTask announcementTask;
    private ConfigUpdater configUpdater;

    private boolean debug;

    public static final double CONFIG_VERSION = 3.0;

    @Override
    public void onEnable() {
        //announcementTask = new AnnouncementTask().runTaskTimer(this, 120L, 120L);

        saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        configUpdater = new ConfigUpdater(this);
        configUpdater.updateConfig();

        debug = this.getConfig().getBoolean("debug");

        announcementManager = new AnnouncementManager();

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