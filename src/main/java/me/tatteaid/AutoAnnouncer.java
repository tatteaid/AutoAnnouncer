package me.tatteaid;

import lombok.Getter;
import me.tatteaid.commands.AnnouncerCommand;
import me.tatteaid.announcements.AnnouncementTask;
import me.tatteaid.utils.config.ConfigCreator;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.logging.Level;

@Getter
public class AutoAnnouncer extends JavaPlugin {

    private BukkitTask announcementTask;
    private ConfigCreator languageFile;
    private ConfigCreator otherTestFile;

    public static final double CONFIG_VERSION = 3.0;

    @Override
    public void onEnable() {
        announcementTask = new AnnouncementTask().runTaskTimer(this, 120L, 120L);

        // doesnt add new content to the config unless the file is deleted
        this.getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        languageFile = new ConfigCreator(this, "language");
        System.out.println(languageFile);
        System.out.println(languageFile.getFile());
        System.out.println(languageFile.getFileName());

        otherTestFile = new ConfigCreator(this, "other_test");
        System.out.println(otherTestFile);
        System.out.println(otherTestFile.getFile());
        System.out.println(otherTestFile.getFileName());

        System.out.println(languageFile.getString("test"));
        System.out.println(otherTestFile.getString("test"));

        System.out.println(getConfig().getValues(false));
        System.out.println(getConfig().getValues(true));

        System.out.println(languageFile.getValues(false));
        System.out.println(languageFile.getValues(true));

        System.out.println(otherTestFile.getValues(false));
        System.out.println(otherTestFile.getValues(true));

        registerCommands();

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) log(Level.INFO, "It is recommend that you use PlaceholderAPI");
    }

    @Override
    public void onDisable() {
        announcementTask.cancel();
    }

    private void registerCommands() {
        this.getCommand("announcer").setExecutor(new AnnouncerCommand());
    }

    public static void log(Level level, String message) {
        Bukkit.getLogger().log(level, message);
    }
}