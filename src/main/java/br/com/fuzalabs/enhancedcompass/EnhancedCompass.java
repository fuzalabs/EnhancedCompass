package br.com.fuzalabs.enhancedcompass;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.fuzalabs.enhancedcompass.commands.CompassCommand;
import br.com.fuzalabs.enhancedcompass.commands.CompassTabCompleter;
import br.com.fuzalabs.enhancedcompass.storage.LocationStorage;

public class EnhancedCompass extends JavaPlugin {

    private FileConfiguration config;
    private LocationStorage storage;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        this.storage = new LocationStorage(getDataFolder());
        this.getCommand("compass").setExecutor(new CompassCommand(config, storage));
        this.getCommand("compass").setTabCompleter(new CompassTabCompleter(storage));
    }
}
