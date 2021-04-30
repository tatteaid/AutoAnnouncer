package me.tatteaid.utils.config;

import lombok.AccessLevel;
import lombok.Getter;
import me.tatteaid.AutoAnnouncer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

@Getter
public class ConfigCreator extends YamlConfiguration {

    @Getter(AccessLevel.NONE)
    private final AutoAnnouncer instance;

    private File file;
    private FileConfiguration config;
    private String fileName;

    public ConfigCreator(AutoAnnouncer instance, String fileName) {
        this.instance = instance;
        this.fileName = fileName;

        loadFile(fileName);
    }

    /* TODO:
        - Create a config file - first time?
        - Load a config file - check a config version for any updates
        - Reload a config - used for the /reload command
        - Save a config - should be a default method, do some testing
     */

    public void loadFile(String fileName) {
        file = new File(instance.getDataFolder(), fileName + ".yml");
        if(!file.exists()) instance.saveResource(fileName + ".yml", true);

        config = YamlConfiguration.loadConfiguration(file);

        try {
            config.save(file);
        } catch (IOException exception) {
            AutoAnnouncer.log(Level.SEVERE, "Could not save config file: " + file.getName());
        }
    }

    public void createFile() {

    }

    public void saveFile() {

    }
}