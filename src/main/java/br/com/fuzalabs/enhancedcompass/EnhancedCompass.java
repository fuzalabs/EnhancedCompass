package br.com.fuzalabs.enhancedcompass;

import org.bukkit.plugin.java.JavaPlugin;

import br.com.fuzalabs.enhancedcompass.commands.CompassAdminCommand;
import br.com.fuzalabs.enhancedcompass.commands.CompassAdminTabCompleter;
import br.com.fuzalabs.enhancedcompass.commands.CompassCommand;
import br.com.fuzalabs.enhancedcompass.commands.CompassTabCompleter;
import br.com.fuzalabs.enhancedcompass.lang.LanguageManager;
import br.com.fuzalabs.enhancedcompass.storage.LocationStorage;

public class EnhancedCompass extends JavaPlugin {

    private LocationStorage storage;
    private LanguageManager languageManager;

    @Override
    public void onEnable() {
        // Save default config file
        saveDefaultConfig();

        // Ensure lang directory exists
        getDataFolder().mkdirs();

        // Initialize components
        this.languageManager = new LanguageManager(this);
        this.storage = new LocationStorage(getDataFolder());

        // Register command and tab completer
        this.getCommand("compass").setExecutor(new CompassCommand(storage, languageManager));
        this.getCommand("compass").setTabCompleter(new CompassTabCompleter(storage));

        // Register admin command and tab completer
        this.getCommand("compassadmin").setExecutor(new CompassAdminCommand(storage, languageManager, this));
        this.getCommand("compassadmin").setTabCompleter(new CompassAdminTabCompleter());

        getLogger().info("EnhancedCompass has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("EnhancedCompass has been disabled!");
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public void reloadPlugin() {
        reloadConfig();
        languageManager.reloadLanguage();
    }
}
