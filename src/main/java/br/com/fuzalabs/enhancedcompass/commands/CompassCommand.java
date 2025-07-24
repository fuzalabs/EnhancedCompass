package br.com.fuzalabs.enhancedcompass.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import br.com.fuzalabs.enhancedcompass.EnhancedCompass;
import br.com.fuzalabs.enhancedcompass.lang.LanguageManager;
import br.com.fuzalabs.enhancedcompass.storage.LocationStorage;

import java.util.Map;

public class CompassCommand implements CommandExecutor {

  private final LocationStorage storage;
  private final LanguageManager languageManager;
  private final EnhancedCompass plugin;

  public CompassCommand(LocationStorage storage, LanguageManager languageManager, EnhancedCompass plugin) {
    this.storage = storage;
    this.languageManager = languageManager;
    this.plugin = plugin;
  }

  private boolean isHoldingCompass(Player player) {
    ItemStack item = player.getInventory().getItemInMainHand();
    return item != null && item.getType() == Material.COMPASS;
  }

  private void sendMessage(CommandSender sender, String key, Map<String, String> replacements) {
    String message = languageManager.getMessage(key, replacements);
    sender.sendMessage(message);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player player))
      return false;

    if (!isHoldingCompass(player)) {
      sendMessage(player, "no_compass", null);
      return true;
    }

    if (args.length < 1) {
      sendMessage(player, "usage", null);
      return true;
    }

    switch (args[0].toLowerCase()) {
      case "set" -> {
        if (args.length < 2) {
          sendMessage(player, "usage", null);
          return true;
        }

        if (args[1].equalsIgnoreCase("global")) {
          if (args.length < 3) {
            sendMessage(player, "usage", null);
            return true;
          }
          storage.saveGlobalLocation(args[2], player.getLocation());
          sendMessage(player, "global_location_saved", Map.of("name", args[2]));
        } else {
          storage.savePlayerLocation(player.getUniqueId(), args[1], player.getLocation());
          sendMessage(player, "location_saved", Map.of("name", args[1]));
        }
      }

      case "reset" -> {
        player.setCompassTarget(player.getWorld().getSpawnLocation());
        sendMessage(player, "compass_reset", null);
      }

      case "reload" -> {
        if (!player.hasPermission("enhancedcompass.admin")) {
          sendMessage(player, "no_permission", null);
          return true;
        }
        plugin.reloadPlugin();
        sendMessage(player, "config_reloaded", null);
      }

      case "debug" -> {
        if (!player.hasPermission("enhancedcompass.admin")) {
          sendMessage(player, "no_permission", null);
          return true;
        }
        languageManager.debugLanguageInfo();
        player.sendMessage("§aDebug information logged to console. Current language: §e" + languageManager.getCurrentLanguage());
      }

      default -> {
        Location targetLoc = null;

        if (args.length == 1) {
          Player targetPlayer = Bukkit.getPlayerExact(args[0]);
          if (targetPlayer != null && targetPlayer.isOnline()) {
            if (!targetPlayer.getWorld().equals(player.getWorld())) {
              sendMessage(player, "target_not_in_same_world", null);
              return true;
            }
            targetLoc = targetPlayer.getLocation();
            sendMessage(player, "compass_point_set", Map.of("target", targetPlayer.getName()));
          } else {
            String key = args[0].toLowerCase();
            targetLoc = storage.getPlayerLocation(player.getUniqueId(), key);
            if (targetLoc == null)
              targetLoc = storage.getGlobalLocation(key);
            if (targetLoc == null) {
              sendMessage(player, "location_not_found", Map.of("name", key));
              return true;
            }
            if (!targetLoc.getWorld().equals(player.getWorld())) {
              sendMessage(player, "target_not_in_same_world", null);
              return true;
            }
            sendMessage(player, "compass_point_set", Map.of("target", args[0]));
          }
        } else if (args.length == 3) {
          try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);
            targetLoc = new Location(player.getWorld(), x, y, z);
            sendMessage(player, "compass_point_set", Map.of("target", x + ", " + y + ", " + z));
          } catch (NumberFormatException e) {
            sendMessage(player, "usage", null);
            return true;
          }
        } else {
          sendMessage(player, "usage", null);
          return true;
        }

        player.setCompassTarget(targetLoc);
      }
    }

    return true;
  }
}
