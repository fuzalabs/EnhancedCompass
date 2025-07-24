package br.com.fuzalabs.enhancedcompass.lang;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.logging.Level;

public class LanguageManager {

  private final JavaPlugin plugin;
  private FileConfiguration languageConfig;
  private FileConfiguration fallbackConfig;
  private String currentLanguage;

  public LanguageManager(JavaPlugin plugin) {
    this.plugin = plugin;
    loadLanguage();
  }

  private void loadLanguage() {
    // Force reload config to ensure we have the latest values
    plugin.reloadConfig();

    // Get language from main config
    String configLang = plugin.getConfig().getString("language", "en");
    this.currentLanguage = configLang;

    plugin.getLogger().info("Loading language: " + configLang);

    // Load primary language file
    this.languageConfig = loadLanguageFile(configLang);

    // Load fallback (English) if not already loaded
    if (!configLang.equals("en")) {
      this.fallbackConfig = loadLanguageFile("en");
    } else {
      this.fallbackConfig = this.languageConfig;
    }

    plugin.getLogger().info("Language loaded successfully: " + currentLanguage);
  }

  private FileConfiguration loadLanguageFile(String language) {
    File langFile = new File(plugin.getDataFolder(), "lang/" + language + ".yml");

    plugin.getLogger().info("Attempting to load language file: " + langFile.getAbsolutePath());

    // Create lang directory if it doesn't exist
    File langDir = new File(plugin.getDataFolder(), "lang");
    if (!langDir.exists()) {
      langDir.mkdirs();
      plugin.getLogger().info("Created lang directory: " + langDir.getAbsolutePath());
    }

    // Save default language files from resources
    if (!langFile.exists()) {
      try {
        plugin.saveResource("lang/" + language + ".yml", false);
        plugin.getLogger().info("Saved default language file: " + language + ".yml");
      } catch (Exception e) {
        plugin.getLogger().warning("Could not save default language file: " + language + ".yml - " + e.getMessage());
      }
    }

    FileConfiguration config = null;

    // Load from file
    if (langFile.exists()) {
      try {
        config = YamlConfiguration.loadConfiguration(langFile);
        plugin.getLogger().info("Successfully loaded language file from disk: " + language + ".yml");
      } catch (Exception e) {
        plugin.getLogger().warning("Error loading language file from disk: " + language + ".yml - " + e.getMessage());
      }
    }

    // Load from resources as fallback
    InputStream defConfigStream = plugin.getResource("lang/" + language + ".yml");
    if (defConfigStream != null) {
      try {
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
        if (config != null) {
          config.setDefaults(defConfig);
        } else {
          config = defConfig;
          plugin.getLogger().info("Loaded language file from resources: " + language + ".yml");
        }
      } catch (Exception e) {
        plugin.getLogger()
            .warning("Error loading language file from resources: " + language + ".yml - " + e.getMessage());
      }
    }

    if (config == null) {
      plugin.getLogger().log(Level.WARNING, "Could not load language file: " + language + ".yml");
      config = new YamlConfiguration(); // Return empty config to prevent crashes
    }

    return config;
  }

  public String getMessage(String key) {
    return getMessage(key, null);
  }

  public String getMessage(String key, Map<String, String> replacements) {
    String message = null;

    // Try to get from current language
    if (languageConfig != null) {
      message = languageConfig.getString("messages." + key);
    }

    // Fallback to English if message not found
    if (message == null && fallbackConfig != null) {
      message = fallbackConfig.getString("messages." + key);
    }

    // Default message if not found in any language file
    if (message == null) {
      message = "Message not configured: " + key;
    }

    // Apply replacements
    if (message != null && replacements != null) {
      for (Map.Entry<String, String> entry : replacements.entrySet()) {
        message = message.replace("{" + entry.getKey() + "}", entry.getValue());
      }
    }

    // Convert color codes and handle line breaks
    if (message != null) {
      message = message.replace("&", "§");
      // Handle manual line breaks if needed
      message = message.replace("\\n", "\n");
    }

    return message != null ? message : "§cMessage not found: " + key;
  }

  public void reloadLanguage() {
    plugin.getLogger().info("Reloading language configuration...");
    loadLanguage();
    plugin.getLogger().info("Language configuration reloaded successfully!");
  }

  public String getCurrentLanguage() {
    return currentLanguage;
  }

  public boolean isLanguageAvailable(String language) {
    File langFile = new File(plugin.getDataFolder(), "lang/" + language + ".yml");
    return langFile.exists() || plugin.getResource("lang/" + language + ".yml") != null;
  }

  public void debugLanguageInfo() {
    plugin.getLogger().info("=== Language Debug Info ===");
    plugin.getLogger().info("Current language: " + currentLanguage);
    plugin.getLogger().info("Config language setting: " + plugin.getConfig().getString("language", "NOT SET"));
    plugin.getLogger().info("Language config loaded: " + (languageConfig != null));
    plugin.getLogger().info("Fallback config loaded: " + (fallbackConfig != null));

    if (languageConfig != null) {
      plugin.getLogger().info("Available message keys: " +
          (languageConfig.getConfigurationSection("messages") != null
              ? languageConfig.getConfigurationSection("messages").getKeys(false).size()
              : 0));
    }
    plugin.getLogger().info("========================");
  }
}
