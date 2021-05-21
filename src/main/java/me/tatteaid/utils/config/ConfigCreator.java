package me.tatteaid.utils.config;

import lombok.Getter;
import me.tatteaid.AutoAnnouncer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.logging.Level;

public class ConfigCreator {

    /*
    TODO:
     - create custom config files
     - reload custom config files
     - save custom config files
     - load custom config files
       - check the defaults and add anything necessary
       - do not worry about any comments
     */

    private final AutoAnnouncer instance;
    @Getter
    private FileConfiguration configFile;

    public ConfigCreator(AutoAnnouncer instance) {
        this.instance = instance;
    }

    public void createFile(String fileName) {
        File file = new File(instance.getDataFolder(), fileName);
        if(!file.exists()) {
            instance.saveResource(fileName, false);
            if(instance.isDebug()) AutoAnnouncer.log(Level.INFO, "File " + file.getName() + " did not exist, creating it now...");
        }
    }

    public void loadFile(String fileName) {
        File file = new File(instance.getDataFolder(), fileName);
        if(file.exists()) {
            configFile = YamlConfiguration.loadConfiguration(file);
        } else {
            AutoAnnouncer.log(Level.SEVERE, "File " + file.getName() + " did not exist, creating it now....");
            createFile(fileName);
        }
    }

    public void reloadFile() {

    }

    public void saveFile() {

    }
}