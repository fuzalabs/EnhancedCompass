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

| Command                | Description                                | Permission            | Example               |
| ---------------------- | ------------------------------------------ | --------------------- | --------------------- |
| `/compass <player>`    | Point compass to an online player          | `enhancedcompass.use` | `/compass Steve`      |
| `/compass <location>`  | Point compass to a saved location          | `enhancedcompass.use` | `/compass home`       |
| `/compass <x> <y> <z>` | Point compass to specific coordinates      | `enhancedcompass.use` | `/compass 100 64 200` |
| `/compass set <name>`  | Save current location as personal waypoint | `enhancedcompass.use` | `/compass set home`   |
| `/compass reset`       | Reset compass to world spawn               | `enhancedcompass.use` | `/compass reset`      |

### Admin Commands

| Command                          | Description                              | Permission              | Example                         |
| -------------------------------- | ---------------------------------------- | ----------------------- | ------------------------------- |
| `/compassadmin reload`           | Reload configuration and language files  | `enhancedcompass.admin` | `/compassadmin reload`          |
| `/compassadmin setglobal <name>` | Save current location as global waypoint | `enhancedcompass.admin` | `/compassadmin setglobal spawn` |
| `/compassadmin debug`            | Show debug information in console        | `enhancedcompass.admin` | `/compassadmin debug`           |
| `/compassadmin info`             | Show plugin information                  | `enhancedcompass.admin` | `/compassadmin info`            |
| `/compassadmin help`             | Show admin command help                  | `enhancedcompass.admin` | `/compassadmin help`            |

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

## Configuration

### Main Configuration (`config.yml`)

```yaml
# Language configuration
# Available languages: en, pt_br
# Default: en (English)
language: en

# Groups configuration
# Define groups and their location limits
# Example:
# groups:
#   default: 3
#   vip: 10
#   staff: 100
groups:
  default: 3
```

**Configuration Options:**

- **`language`**: Sets the default language for messages. Currently supports `en` (English) and `pt_br` (Portuguese Brazil)
- **`groups`**: Defines permission groups and their location limits. Players with higher group permissions can save more locations

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

### Changing Language

1. Edit `language` setting in `config.yml`
2. Run `/compassadmin reload` or restart the server

### Custom Messages

Language messages are controlled by the individual language files (`en.yml`, `pt_br.yml`) in the `lang/` directory. You can edit these files directly to customize messages, but changes will be overwritten when the plugin is updated.

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
/compassadmin setglobal spawn
/compassadmin setglobal pvp_arena
```

### Advanced Usage

```bash
# Reset compass to world spawn
/compass reset

# Admin: Reload configuration
/compassadmin reload

# Admin: Show plugin information
/compassadmin info

# Admin: Debug language information
/compassadmin debug
```

## Troubleshooting

### Common Issues

#### "You need to be holding a compass"

**Cause**: Player is not holding a compass in their main hand.

**Solutions**:

- Hold a compass in your main hand
- This requirement is currently hardcoded and cannot be bypassed

#### "Player not found or offline"

**Cause**: Target player is not online or name is misspelled.

**Solutions**:

- Check the player's name spelling
- Ensure the target player is online
- Use tab completion to auto-complete player names

#### "Location not found"

**Cause**: Trying to navigate to a location that doesn't exist.

**Solutions**:

- Check spelling of location name (names are case-insensitive)
- Verify if it's a personal or global location
- Make sure you've saved the location first with `/compass set <name>`

#### "The target is not in the same world"

**Cause**: Trying to point compass to a target in a different world.

**Solutions**:

- Travel to the same world as your target
- Cross-world compass pointing is currently not supported

#### "You have reached your location limit"

**Cause**: Player has hit their group's location limit.

**Solutions**:

- Currently no way to delete locations (feature not yet implemented)
- Ask an admin for a higher group permission
- Admin can increase group limits in config.yml

### Performance Issues

#### High memory usage

- Clean up unused player location files in `player-locations/` directory
- Consider regular maintenance of location files

#### Slow commands

- Check for plugin conflicts
- Verify server TPS is stable
- Update to the latest plugin version

### Configuration Issues

#### Changes not taking effect

- Run `/compassadmin reload` after editing config files
- Check YAML syntax is valid
- Restart server if reload doesn't work

#### Language not changing

- Verify language file exists in `lang/` folder
- Check `language` setting in config.yml
- Ensure file encoding is UTF-8

---

## Support

Need help? Here's how to get support:

1. **Check this documentation** - Most questions are answered here
2. **Search existing issues** - Your problem might already be reported
3. **Create a new issue** - Use our GitHub issue templates
4. **Contact support** - Email us for urgent issues

### Reporting Bugs

When reporting bugs, please include:

- Plugin version
- Server version and type (Bukkit/Spigot/Paper)
- Steps to reproduce
- Error messages or logs
- Configuration files (if relevant)

---

**Last updated**: August 31, 2025  
**Plugin version**: 0.1.0
