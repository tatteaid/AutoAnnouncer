package me.tatteaid.utils.config;

import me.tatteaid.AutoAnnouncer;
import org.apache.commons.lang.reflect.FieldUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.FileUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class ConfigUpdater {

    private final AutoAnnouncer instance;

    public ConfigUpdater(AutoAnnouncer instance) {
        this.instance = instance;
    }

    public void updateConfig() {
        if (!instance.getConfig().contains("CONFIG_VERSION")) {
            AutoAnnouncer.log(Level.SEVERE, "The configuration does not have a config_version.");
            AutoAnnouncer.log(Level.SEVERE, "Resetting the configuration file.");
            // TODO: perform reset logic, wipe the file and replace it.
            // TODO: move up the file path?
            return;
        }

        if (instance.getConfig().getDouble("CONFIG_VERSION") == AutoAnnouncer.CONFIG_VERSION) {
            AutoAnnouncer.log(Level.INFO, "The configuration is up to date.");
            return;
        }

        if (instance.getConfig().getDouble("CONFIG_VERSION") != AutoAnnouncer.CONFIG_VERSION) {
            File file = new File(instance.getDataFolder(), "config.yml");

            // handles the renaming logic behind the old config file
            try {
                Files.move(file.toPath(), file.toPath().resolveSibling("config.old.yml"));
                AutoAnnouncer.log(Level.INFO, "Renamed the old configuration file.");
            } catch (IOException exception) {
                AutoAnnouncer.log(Level.SEVERE, "Could not rename the configuration file.");
            }

            File oldFile = new File(instance.getDataFolder(), "config.old.yml");
            FileConfiguration oldConfig = YamlConfiguration.loadConfiguration(oldFile);

            // creates a new config which the values will be copied over too
            instance.saveDefaultConfig();

            Map<String, Object> oldValues = oldConfig.getValues(false);
            List<String> comments = new ArrayList<>();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(oldFile));
            } catch (FileNotFoundException exception) {
                AutoAnnouncer.log(Level.SEVERE, "Could not find the config file.");
            }

            // TODO: logic that transfer old data to new data and keeps the new values

            // handles the deleting of the old config file
            try {
                Files.delete(oldFile.toPath());
                AutoAnnouncer.log(Level.INFO, "Deleted the old configuration file.");
            } catch (IOException exception) {
                AutoAnnouncer.log(Level.SEVERE, "Could not delete the old configuration file.");
            }

            AutoAnnouncer.log(Level.INFO, "File path: " + file.toPath());
            AutoAnnouncer.log(Level.INFO, "Old file path: " + oldFile.toPath());
            AutoAnnouncer.log(Level.INFO, "Finished updating the configuration file...");
            return;
        }
    }
}