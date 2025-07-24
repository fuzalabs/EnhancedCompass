package br.com.fuzalabs.enhancedcompass.commands;

import br.com.fuzalabs.enhancedcompass.storage.LocationStorage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

public class CompassTabCompleter implements TabCompleter {

    private final LocationStorage storage;

    public CompassTabCompleter(LocationStorage storage) {
        this.storage = storage;
    }

    @Override
    public List<String> onTabComplete(
            CommandSender sender, Command command, String alias, String[] args) {

        if (!(sender instanceof Player player))
            return List.of();

        UUID uuid = player.getUniqueId();

        if (args.length == 1) {
            List<String> base = new ArrayList<>();

            // Adiciona comandos primários
            base.add("set");
            base.add("reset");

            // Adiciona jogadores online
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.equals(player))
                    base.add(online.getName());
            }

            // Adiciona locais salvos
            base.addAll(storage.getAllPlayerLocationNames(uuid));
            base.addAll(storage.getAllGlobalLocationNames());

            return filterByPrefix(base, args[0]);
        }

        return List.of();
    }

    private List<String> filterByPrefix(List<String> options, String prefix) {
        String lower = prefix.toLowerCase(Locale.ROOT);
        return options.stream()
                .filter(s -> s.toLowerCase(Locale.ROOT).startsWith(lower))
                .sorted()
                .collect(Collectors.toList());
    }
}
