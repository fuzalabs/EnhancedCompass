package br.com.fuzalabs.enhancedcompass.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CompassAdminTabCompleter implements TabCompleter {

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

    if (!sender.hasPermission("enhancedcompass.admin")) {
      return new ArrayList<>();
    }

    if (args.length == 1) {
      List<String> commands = new ArrayList<>();
      commands.add("reload");
      commands.add("debug");
      commands.add("info");
      commands.add("help");

      // setglobal only for players
      if (sender instanceof Player) {
        commands.add("setglobal");
      }

      return commands.stream()
          .filter(cmd -> cmd.toLowerCase().startsWith(args[0].toLowerCase()))
          .toList();
    }

    if (args.length == 2 && args[0].equalsIgnoreCase("setglobal")) {
      // Suggest some common global location names
      List<String> suggestions = new ArrayList<>();
      suggestions.add("spawn");
      suggestions.add("shop");
      suggestions.add("arena");
      suggestions.add("warp");
      return suggestions.stream()
          .filter(name -> name.toLowerCase().startsWith(args[1].toLowerCase()))
          .toList();
    }

    return new ArrayList<>();
  }
}
