package me.tatteaid.utils.config;

import me.tatteaid.AutoAnnouncer;
import org.apache.commons.lang.reflect.FieldUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.FileUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
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

            // creates a new default config which the values will be copied over too
            instance.saveDefaultConfig();

            // TODO: logic that transfer old data to new data and keeps the new values
            Map<String, Object> oldValues = oldConfig.getValues(false);
            List<String> defaultLines = new ArrayList<>();
            List<String> newLines = new ArrayList<>();

            /**
             * Handles the scanning & storing of content in config.old.yml & config.yml
             * This will deal with comparing & updating both files later on
             *
             * oldFileScanner - scans the old files content (config.old.yml) and stores content in the oldLines arraylist
             * newFileScanner - scans the newly generated files content (config.yml) and stores content in the newLines arraylist
             */
            try {
                Scanner newDefaultLineScanner = new Scanner(file);

                while(newDefaultLineScanner.hasNextLine()) {
                    defaultLines.add(newDefaultLineScanner.nextLine());
                    AutoAnnouncer.log(Level.INFO, "New Lines Size: " + defaultLines.size());
                }

                newDefaultLineScanner.close();
            } catch (FileNotFoundException exception) {
                AutoAnnouncer.log(Level.SEVERE, "Could not find the old configuration file to scan from.");
            }

            for(String line : defaultLines) {
                if(!line.startsWith("CONFIG_VERSION")) {
                    System.out.println("Line Output: " + line);

                    for(String node : oldValues.keySet()) {
                        System.out.println("Old Value Node Output: " + node);
                    }
                } else {
                    // do nothing, we do not want to change the new config version'
                    System.out.println("Line Starts With CONFIG_VERSION: Nothing");
                }
            }

            // handles the deleting of the old config file
            /*try {
                Files.delete(oldFile.toPath());
                AutoAnnouncer.log(Level.INFO, "Deleted the old configuration file.");
            } catch (IOException exception) {
                AutoAnnouncer.log(Level.SEVERE, "Could not delete the old configuration file.");
            }*/

            AutoAnnouncer.log(Level.INFO, "File path: " + file.toPath());
            AutoAnnouncer.log(Level.INFO, "Old file path: " + oldFile.toPath());
            AutoAnnouncer.log(Level.INFO, "Finished updating the configuration file...");
        }
    }
}