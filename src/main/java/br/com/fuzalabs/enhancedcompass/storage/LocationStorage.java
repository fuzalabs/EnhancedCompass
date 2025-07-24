package br.com.fuzalabs.enhancedcompass.storage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LocationStorage {

    private final File pluginFolder;
    private final File playerFolder;
    private final File globalFile;
    private final FileConfiguration globalConfig;

    public LocationStorage(File dataFolder) {
        this.pluginFolder = dataFolder;
        this.playerFolder = new File(dataFolder, "player-locations");
        if (!playerFolder.exists())
            playerFolder.mkdirs();

        this.globalFile = new File(dataFolder, "global-locations.yml");
        if (!globalFile.exists()) {
            try {
                globalFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.globalConfig = YamlConfiguration.loadConfiguration(globalFile);
    }

    // ------------- GLOBAL -------------
    public void saveGlobalLocation(String name, Location location) {
        setLocation(globalConfig, name.toLowerCase(), location);
        saveConfig(globalConfig, globalFile);
    }

    public Location getGlobalLocation(String name) {
        return getLocation(globalConfig, name.toLowerCase());
    }

    public boolean hasGlobalLocation(String name) {
        return globalConfig.contains(name.toLowerCase());
    }

    // ------------- PLAYER -------------
    private FileConfiguration getPlayerConfig(UUID uuid) {
        File file = new File(playerFolder, uuid.toString() + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    private File getPlayerFile(UUID uuid) {
        return new File(playerFolder, uuid.toString() + ".yml");
    }

    public void savePlayerLocation(UUID uuid, String name, Location location) {
        FileConfiguration config = getPlayerConfig(uuid);
        setLocation(config, name.toLowerCase(), location);
        saveConfig(config, getPlayerFile(uuid));
    }

    public Location getPlayerLocation(UUID uuid, String name) {
        FileConfiguration config = getPlayerConfig(uuid);
        return getLocation(config, name.toLowerCase());
    }

    public boolean hasPlayerLocation(UUID uuid, String name) {
        FileConfiguration config = getPlayerConfig(uuid);
        return config.contains(name.toLowerCase());
    }

    // ------------- UTIL -------------
    private void setLocation(FileConfiguration config, String key, Location loc) {
        config.set(key + ".world", loc.getWorld().getName());
        config.set(key + ".x", loc.getX());
        config.set(key + ".y", loc.getY());
        config.set(key + ".z", loc.getZ());
        config.set(key + ".yaw", loc.getYaw());
        config.set(key + ".pitch", loc.getPitch());
    }

    private Location getLocation(FileConfiguration config, String key) {
        if (!config.contains(key))
            return null;
        World world = Bukkit.getWorld(config.getString(key + ".world"));
        if (world == null)
            return null;
        double x = config.getDouble(key + ".x");
        double y = config.getDouble(key + ".y");
        double z = config.getDouble(key + ".z");
        float yaw = (float) config.getDouble(key + ".yaw");
        float pitch = (float) config.getDouble(key + ".pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    private void saveConfig(FileConfiguration config, File file) {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllPlayerLocationNames(UUID uuid) {
        FileConfiguration config = getPlayerConfig(uuid);
        return new ArrayList<>(config.getKeys(false));
    }

    public List<String> getAllGlobalLocationNames() {
        return new ArrayList<>(globalConfig.getKeys(false));
    }
}
