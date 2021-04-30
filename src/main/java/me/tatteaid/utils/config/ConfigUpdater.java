package me.tatteaid.utils.config;

import me.tatteaid.AutoAnnouncer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigUpdater {

    private final AutoAnnouncer instance;

    public ConfigUpdater(AutoAnnouncer instance) {
        this.instance = instance;
    }

    public void updateConfig() {
        if(instance.getConfig().getInt("CONFIG_VERSION") != AutoAnnouncer.CONFIG_VERSION) {

        }

        File oldFile = new File(instance.getDataFolder(), "config.yml");
        FileConfiguration oldConfig = YamlConfiguration.loadConfiguration(oldFile);

        try {
            oldConfig.load(oldFile);
        } catch (IOException | InvalidConfigurationException exception) {
            AutoAnnouncer.log(Level.SEVERE, "Failed to load the file: " + oldFile.getName());
        }

        oldConfig.getValues(false);
    }
}