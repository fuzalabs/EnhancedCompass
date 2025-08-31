# 📖 EnhancedCompass Documentation

This comprehensive guide covers all features, configuration options, and advanced usage of the EnhancedCompass plugin.

## Table of Contents

- [Installation](#installation)
- [Commands](#commands)
- [Permissions](#permissions)
- [Configuration](#configuration)
- [Group System](#group-system)
- [Multi-Language Support](#multi-language-support)
- [File Structure](#file-structure)
- [Usage Examples](#usage-examples)
- [Troubleshooting](#troubleshooting)
- [API Reference](#api-reference)

## Installation

### Requirements

- **Minecraft**: 1.8 - 1.20+
- **Java**: 8 or higher
- **Server**: Bukkit, Spigot, or Paper

### Steps

1. Download the latest release from [GitHub Releases](https://github.com/yourusername/EnhancedCompass/releases)
2. Place the JAR file in your server's `plugins/` directory
3. Restart your server
4. The plugin will automatically generate default configuration files

## Commands

### Player Commands

| Command                      | Description                                | Permission              | Example                     |
| ---------------------------- | ------------------------------------------ | ----------------------- | --------------------------- |
| `/compass <player>`          | Point compass to an online player          | `enhancedcompass.use`   | `/compass Steve`            |
| `/compass <location>`        | Point compass to a saved location          | `enhancedcompass.use`   | `/compass home`             |
| `/compass <x> <y> <z>`       | Point compass to specific coordinates      | `enhancedcompass.use`   | `/compass 100 64 200`       |
| `/compass set <name>`        | Save current location as personal waypoint | `enhancedcompass.use`   | `/compass set home`         |
| `/compass set global <name>` | Save current location as global waypoint   | `enhancedcompass.admin` | `/compass set global spawn` |
| `/compass list`              | List all saved locations                   | `enhancedcompass.use`   | `/compass list`             |
| `/compass delete <name>`     | Delete a saved location                    | `enhancedcompass.use`   | `/compass delete home`      |
| `/compass reset`             | Reset compass to world spawn               | `enhancedcompass.use`   | `/compass reset`            |

### Admin Commands

| Command                                     | Description                             | Permission              | Example                            |
| ------------------------------------------- | --------------------------------------- | ----------------------- | ---------------------------------- |
| `/compass reload`                           | Reload configuration and language files | `enhancedcompass.admin` | `/compass reload`                  |
| `/compass admin list <player>`              | List locations of specific player       | `enhancedcompass.admin` | `/compass admin list Steve`        |
| `/compass admin delete <player> <location>` | Delete player's location                | `enhancedcompass.admin` | `/compass admin delete Steve home` |

## Permissions

### Basic Permissions

| Permission              | Description                       | Default |
| ----------------------- | --------------------------------- | ------- |
| `enhancedcompass.use`   | Access to basic compass commands  | `true`  |
| `enhancedcompass.admin` | Access to administrative commands | `op`    |

### Group Permissions

Location limits are controlled by group permissions. Players receive the highest limit from their assigned groups.

| Permission                      | Description                     | Default Limit |
| ------------------------------- | ------------------------------- | ------------- |
| `enhancedcompass.group.default` | Default group with basic limits | 3 locations   |
| `enhancedcompass.group.vip`     | VIP group with increased limits | 10 locations  |
| `enhancedcompass.group.staff`   | Staff group with high limits    | 100 locations |

### Advanced Permissions

| Permission                       | Description                            |
| -------------------------------- | -------------------------------------- |
| `enhancedcompass.bypass.compass` | Use commands without holding a compass |
| `enhancedcompass.bypass.world`   | Point compass across different worlds  |
| `enhancedcompass.unlimited`      | Bypass all location limits             |

## Configuration

### Main Configuration (`config.yml`)

```yaml
# EnhancedCompass Configuration
version: 1.0

# Group-based location limits
groups:
  default: 3 # Default players can save 3 locations
  vip: 10 # VIP players can save 10 locations
  staff: 100 # Staff can save 100 locations

# Plugin settings
settings:
  # Require players to hold a compass to use commands
  require_compass: true

  # Allow cross-world compass pointing
  allow_cross_world: false

  # Save location on death
  save_death_location: true

  # Auto-save interval (in minutes, 0 to disable)
  auto_save_interval: 5

# Default language (supports: en, pt_br, es, fr, de)
language: en

# Custom messages (overrides language files)
messages:
  no_compass: "&cYou need to be holding a compass to use this command."
  player_not_found: "&cPlayer '&e{player}&c' not found or offline."
  target_not_in_same_world: "&cThe target is not in the same world as you."
  location_saved: "&aLocation '&e{name}&a' successfully saved!"
  global_location_saved: "&aGlobal location '&e{name}&a' saved successfully!"
  location_not_found: "&cLocation '&e{name}&c' not found."
  location_deleted: "&aLocation '&e{name}&a' deleted successfully!"
  location_limit_reached: "&cYou have reached your location limit (&e{limit}&c). Delete some locations or upgrade your group."
  compass_point_set: "&aYour compass now points to '&e{target}&a'."
  compass_reset: "&aYour compass has been reset to the world spawn."
  invalid_coordinates: "&cInvalid coordinates. Please use whole numbers."
  same_world_required: "&cYou can only point to locations in the same world."

  # List command messages
  locations_header: "&e--- Your Saved Locations ---"
  locations_entry: "&a{index}. &e{name} &7({world}: {x}, {y}, {z})"
  global_locations_header: "&e--- Global Locations ---"
  no_locations: "&cYou have no saved locations."

  # Admin messages
  config_reloaded: "&aConfiguration and language files reloaded successfully!"
  player_locations_header: "&e--- Locations for {player} ---"
  admin_location_deleted: "&aDeleted location '&e{name}&a' from player '&e{player}&a'."

  # Usage messages
  usage_main: "&eUsage: /compass <player|location|coordinates|set|list|delete|reset>"
  usage_set: "&eUsage: /compass set [global] <name>"
  usage_delete: "&eUsage: /compass delete <name>"
  usage_admin: "&eUsage: /compass admin <list|delete> <player> [location]"
```

### Language Files

The plugin supports multiple languages through individual language files located in `plugins/EnhancedCompass/lang/`.

#### English (`en.yml`)

```yaml
messages:
  no_compass: "&cYou need to be holding a compass to use this command."
  player_not_found: "&cPlayer '{player}' not found or offline."
  # ... (full English translations)
```

#### Portuguese Brazil (`pt_br.yml`)

```yaml
messages:
  no_compass: "&cVocê precisa estar segurando uma bússola para usar este comando."
  player_not_found: "&cJogador '{player}' não encontrado ou offline."
  # ... (full Portuguese translations)
```

## Group System

The group system allows you to set different location limits for different types of players.

### Setting Up Groups

1. **Configure limits** in `config.yml`:

```yaml
groups:
  default: 3
  vip: 10
  premium: 25
  staff: 100
```

2. **Assign permissions** to players or groups:

```
/lp user Steve permission set enhancedcompass.group.vip
/lp group VIP permission set enhancedcompass.group.vip
```

### Group Priority

If a player has multiple group permissions, the plugin uses the **highest** limit available. For example:

- Player has `enhancedcompass.group.default` (3 locations) and `enhancedcompass.group.vip` (10 locations)
- The plugin will allow 10 locations (VIP limit)

### Special Groups

- **Unlimited**: Use permission `enhancedcompass.unlimited` to bypass all limits
- **Custom groups**: Add any group name to the config with a custom limit

## Multi-Language Support

### Available Languages

- **English** (`en`)
- **Portuguese Brazil** (`pt_br`)
- **Spanish** (`es`) - Coming soon
- **French** (`fr`) - Coming soon
- **German** (`de`) - Coming soon

### Changing Language

1. Edit `language` setting in `config.yml`
2. Run `/compass reload` or restart the server

### Custom Messages

You can override specific messages in `config.yml` under the `messages` section without changing the language file.

### Color Codes

Use Minecraft color codes with `&`:

| Code | Color       | Code | Color        |
| ---- | ----------- | ---- | ------------ |
| `&0` | Black       | `&8` | Dark Gray    |
| `&1` | Dark Blue   | `&9` | Blue         |
| `&2` | Dark Green  | `&a` | Green        |
| `&3` | Dark Aqua   | `&b` | Aqua         |
| `&4` | Dark Red    | `&c` | Red          |
| `&5` | Dark Purple | `&d` | Light Purple |
| `&6` | Gold        | `&e` | Yellow       |
| `&7` | Gray        | `&f` | White        |

#### Format Codes

- `&l` - Bold
- `&o` - Italic
- `&n` - Underlined
- `&m` - Strikethrough
- `&k` - Obfuscated
- `&r` - Reset

## File Structure

```
plugins/EnhancedCompass/
├── config.yml                     # Main configuration
├── global-locations.yml           # Server-wide waypoints
├── lang/                          # Language files
│   ├── en.yml                     # English translations
│   ├── pt_br.yml                  # Portuguese Brazil
│   └── ...                       # Other languages
└── player-locations/              # Individual player data
    ├── 550e8400-e29b-41d4-a716-446655440000.yml
    ├── 6ba7b810-9dad-11d1-80b4-00c04fd430c8.yml
    └── ...
```

### Player Location Files

Each player's locations are stored in individual YAML files named by their UUID:

```yaml
# player-locations/550e8400-e29b-41d4-a716-446655440000.yml
locations:
  home:
    world: world
    x: 100.5
    y: 64.0
    z: 200.7
  shop:
    world: world
    x: -50.0
    y: 70.0
    z: 150.2
```

### Global Locations File

```yaml
# global-locations.yml
locations:
  spawn:
    world: world
    x: 0.0
    y: 64.0
    z: 0.0
  pvp:
    world: world_pvp
    x: 100.0
    y: 80.0
    z: -200.0
```

## Usage Examples

### Basic Navigation

```bash
# Point to a player
/compass Steve
/compass NotchFan123

# Point to saved locations
/compass home
/compass shop
/compass base

# Point to coordinates
/compass 0 64 0
/compass 1000 100 -500
```

### Managing Locations

```bash
# Save your current location
/compass set home
/compass set shop
/compass set secret_base

# Save global locations (admin only)
/compass set global spawn
/compass set global pvp_arena

# List all your locations
/compass list

# Delete a location
/compass delete old_base
```

### Advanced Usage

```bash
# Reset compass to world spawn
/compass reset

# Admin: List another player's locations
/compass admin list Steve

# Admin: Delete another player's location
/compass admin delete Steve old_house

# Reload configuration
/compass reload
```

## Troubleshooting

### Common Issues

#### "You need to be holding a compass"

**Cause**: Player is not holding a compass in their main hand.

**Solutions**:

- Hold a compass in your main hand
- Ask an admin to give you the `enhancedcompass.bypass.compass` permission
- Admin can set `require_compass: false` in config.yml

#### "Player not found or offline"

**Cause**: Target player is not online or name is misspelled.

**Solutions**:

- Check the player's name spelling
- Ensure the target player is online
- Use tab completion to auto-complete player names

#### "Location not found"

**Cause**: Trying to navigate to a location that doesn't exist.

**Solutions**:

- Use `/compass list` to see available locations
- Check spelling of location name
- Verify if it's a personal or global location

#### "The target is not in the same world"

**Cause**: Trying to point compass to a target in a different world.

**Solutions**:

- Travel to the same world as your target
- Ask an admin for `enhancedcompass.bypass.world` permission
- Admin can enable `allow_cross_world: true` in config

#### "You have reached your location limit"

**Cause**: Player has hit their group's location limit.

**Solutions**:

- Delete unused locations with `/compass delete <name>`
- Ask an admin for a higher group permission
- Admin can increase group limits in config.yml

### Performance Issues

#### High memory usage

- Reduce `auto_save_interval` or set to 0
- Clean up unused player location files
- Consider using a database storage plugin

#### Slow commands

- Check for plugin conflicts
- Verify server TPS is stable
- Update to the latest plugin version

### Configuration Issues

#### Changes not taking effect

- Run `/compass reload` after editing config files
- Check YAML syntax is valid
- Restart server if reload doesn't work

#### Language not changing

- Verify language file exists in `lang/` folder
- Check `language` setting in config.yml
- Ensure file encoding is UTF-8

## API Reference

### For Developers

EnhancedCompass provides a simple API for other plugins to interact with location data.

#### Getting the API

```java
import br.com.fuzalabs.enhancedcompass.api.EnhancedCompassAPI;

// Get API instance
EnhancedCompassAPI api = EnhancedCompassAPI.getInstance();
```

#### Basic Methods

```java
// Check if player has a location
boolean hasLocation = api.hasLocation(player.getUniqueId(), "home");

// Get player's location
Location location = api.getLocation(player.getUniqueId(), "home");

// Save a location for player
api.saveLocation(player.getUniqueId(), "home", player.getLocation());

// Delete a location
api.deleteLocation(player.getUniqueId(), "home");

// Get all locations for player
Map<String, Location> locations = api.getPlayerLocations(player.getUniqueId());

// Point player's compass to location
api.pointCompass(player, location);
```

#### Events

```java
import br.com.fuzalabs.enhancedcompass.events.*;

@EventHandler
public void onLocationSave(LocationSaveEvent event) {
    Player player = event.getPlayer();
    String locationName = event.getLocationName();
    Location location = event.getLocation();

    // Your code here
}

@EventHandler
public void onCompassPoint(CompassPointEvent event) {
    Player player = event.getPlayer();
    Location target = event.getTarget();

    // Your code here
}
```

#### Maven Dependency

```xml
<repository>
    <id>fuzalabs-repo</id>
    <url>https://repo.fuzalabs.com/releases</url>
</repository>

<dependency>
    <groupId>br.com.fuzalabs</groupId>
    <artifactId>enhancedcompass</artifactId>
    <version>1.0.0</version>
    <scope>provided</scope>
</dependency>
```

---

## Support

Need help? Here's how to get support:

1. **Check this documentation** - Most questions are answered here
2. **Search existing issues** - Your problem might already be reported
3. **Create a new issue** - Use our GitHub issue templates
4. **Join our Discord** - Get help from the community
5. **Contact support** - Email us for urgent issues

### Reporting Bugs

When reporting bugs, please include:

- Plugin version
- Server version and type (Bukkit/Spigot/Paper)
- Steps to reproduce
- Error messages or logs
- Configuration files (if relevant)

---

**Last updated**: August 31, 2025  
**Plugin version**: 1.0.0
