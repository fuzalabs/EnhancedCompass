package br.com.fuzalabs.enhancedcompass.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.fuzalabs.enhancedcompass.EnhancedCompass;
import br.com.fuzalabs.enhancedcompass.lang.LanguageManager;
import br.com.fuzalabs.enhancedcompass.storage.LocationStorage;

import java.util.Map;

public class CompassAdminCommand implements CommandExecutor {

  private final LocationStorage storage;
  private final LanguageManager languageManager;
  private final EnhancedCompass plugin;

  public CompassAdminCommand(LocationStorage storage, LanguageManager languageManager, EnhancedCompass plugin) {
    this.storage = storage;
    this.languageManager = languageManager;
    this.plugin = plugin;
  }

  private void sendMessage(CommandSender sender, String key, Map<String, String> replacements) {
    String message = languageManager.getMessage(key, replacements);
    sender.sendMessage(message);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("enhancedcompass.admin")) {
      sendMessage(sender, "no_permission", null);
      return true;
    }

    if (args.length < 1) {
      sendMessage(sender, "admin_usage", null);
      return true;
    }

    switch (args[0].toLowerCase()) {
      case "reload" -> {
        plugin.reloadPlugin();
        sendMessage(sender, "config_reloaded", null);
      }

      case "debug" -> {
        languageManager.debugLanguageInfo();
        sender.sendMessage(
            "§aDebug information logged to console. Current language: §e" + languageManager.getCurrentLanguage());
      }

      case "setglobal" -> {
        if (!(sender instanceof Player player)) {
          sender.sendMessage("§cThis command can only be used by players.");
          return true;
        }

        if (args.length < 2) {
          sendMessage(sender, "admin_usage", null);
          return true;
        }

        storage.saveGlobalLocation(args[1], player.getLocation());
        sendMessage(sender, "global_location_saved", Map.of("name", args[1]));
      }

      case "info" -> {
        sender.sendMessage("§6=== EnhancedCompass Admin Info ===");
        sender.sendMessage("§eVersion: §f" + plugin.getPluginMeta().getVersion());
        sender.sendMessage("§eCurrent Language: §f" + languageManager.getCurrentLanguage());
        sender.sendMessage(
            "§eLanguage Available: §f" + languageManager.isLanguageAvailable(languageManager.getCurrentLanguage()));
        sender.sendMessage("§ePlugin Enabled: §f" + plugin.isEnabled());
        sender.sendMessage("§6==============================");
      }

      case "help" -> {
        sendMessage(sender, "admin_usage", null);
      }

      default -> {
        sendMessage(sender, "admin_usage", null);
      }
    }

    return true;
  }
}
