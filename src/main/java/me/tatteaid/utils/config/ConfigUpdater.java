package me.tatteaid.utils.config;

import me.tatteaid.AutoAnnouncer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;

/**
 * For the general idea of the configuration updater with some modifications made to adapt to this plugin, all credits go to...
 *
 * @author mfnalex
 * @see https://github.com/JEFF-Media-GbR/Spigot-ChestSort/blob/master/src/main/java/de/jeff_media/ChestSort/ChestSortConfigUpdater.java
 */
// TODO: document code much better tomorrow
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
                if(instance.isDebug()) AutoAnnouncer.log(Level.INFO, "Renamed the old configuration file.");
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
                }

                newDefaultLineScanner.close();
            } catch (FileNotFoundException exception) {
                AutoAnnouncer.log(Level.SEVERE, "Could not find the old configuration file to scan from.");
            }

            for(String newLine : defaultLines) {
                if(newLine.startsWith("CONFIG_VERSION")) {
                    // do nothing, we do not want to change the new config version
                } else {
                    for(String node : oldValues.keySet()) {
                        if(newLine.startsWith(node + ":")) {
                            newLine = node + ": " + "'" + oldValues.get(node).toString() + "'";
                            break;
                        }
                    }
                }

                if(newLine != null) {
                    newLines.add(newLine);
                }
            }

            // writes the content to the new config file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                String[] newLineArray = newLines.toArray(new String[newLines.size()]);

                for (String line : newLineArray) {
                    writer.write(line + "\n");
                }

                writer.close();
            } catch (IOException exception) {
                AutoAnnouncer.log(Level.SEVERE, "Could not write to the new configuration file.");
            }

            // handles the deleting of the old config file
            try {
                Files.delete(oldFile.toPath());
                if(instance.isDebug()) AutoAnnouncer.log(Level.INFO, "Deleted the old configuration file.");
            } catch (IOException exception) {
                AutoAnnouncer.log(Level.SEVERE, "Could not delete the old configuration file.");
            }

            AutoAnnouncer.log(Level.INFO, "Finished updating the configuration file...");
        }
    }
}