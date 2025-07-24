# 🧭 EnhancedCompass

> A powerful and intuitive compass plugin for Minecraft servers that allows players to point their compass to players, saved locations, or specific coordinates.

[![Minecraft](https://img.shields.io/badge/Minecraft-1.13+-green.svg)](https://minecraft.net)
[![Bukkit](https://img.shields.io/badge/Bukkit-Compatible-blue.svg)](https://bukkit.org)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Version](https://img.shields.io/badge/Version-1.0.0-red.svg)](#)

## ✨ Features

- 🎯 **Point to Players**: Direct your compass to any online player
- 📍 **Save Locations**: Store and navigate to personal waypoints
- 🌍 **Global Locations**: Server-wide waypoints accessible by all players
- 📐 **Coordinate Navigation**: Point to specific X, Y, Z coordinates
- 🔄 **Compass Reset**: Restore compass to world spawn
- 🔧 **Configurable Messages**: Customize all plugin messages
- 🎨 **Color Support**: Full color code support in messages
- 🗂️ **Data Persistence**: Locations saved between server restarts

## 📋 Requirements

- **Minecraft**: 1.13 or higher
- **Server Software**: Bukkit, Spigot, or Paper
- **Java**: 8 or higher

## 🚀 Installation

1. Download the latest `EnhancedCompass.jar` from the releases page
2. Place the JAR file in your server's `plugins` folder
3. Restart your server
4. The plugin will generate a `config.yml` file automatically

## 🎮 Commands

| Command                      | Description                       | Permission            |
| ---------------------------- | --------------------------------- | --------------------- |
| `/compass <player>`          | Point compass to a player         | `enhancedcompass.use` |
| `/compass <location>`        | Point compass to a saved location | `enhancedcompass.use` |
| `/compass <x> <y> <z>`       | Point compass to coordinates      | `enhancedcompass.use` |
| `/compass set <name>`        | Save current location             | `enhancedcompass.use` |
| `/compass set global <name>` | Save global location              | `enhancedcompass.use` |
| `/compass reset`             | Reset compass to world spawn      | `enhancedcompass.use` |

## 📖 Usage Examples

### Basic Navigation

```
/compass Steve          # Point to player Steve
/compass home           # Point to saved location "home"
/compass 100 64 200     # Point to coordinates (100, 64, 200)
/compass reset          # Reset to world spawn
```

### Saving Locations

```
/compass set home       # Save current location as "home"
/compass set shop       # Save current location as "shop"
/compass set global spawn    # Save global location "spawn"
```

### Important Notes

- 🧭 You must be holding a compass to use any command
- 🌍 Target must be in the same world as you
- 📝 Location names are case-insensitive
- 💾 Personal locations are saved per player

## ⚙️ Configuration

The `config.yml` file allows you to customize all messages:

```yaml
messages:
  no_compass: "&cYou need to be holding a compass to use this command."
  player_not_found: "&cPlayer not found or offline."
  target_not_in_same_world: "&cThe target is not in the same world as you."
  location_saved: "&aLocation successfully saved as '&e{name}&a'."
  global_location_saved: "&aGlobal location saved as '&e{name}&a'."
  location_not_found: "&cLocation '&e{name}&c' not found."
  compass_point_set: "&aYour compass now points to '&e{target}&a'."
  compass_reset: "&aYour compass has been reset to the world spawn."
  usage: "&eUsage: /compass <player|location|coordinates>\n       /compass set <name>\n       /compass set global <name>\n       /compass reset"
```

### Color Codes

Use `&` followed by a color code:

- `&a` = Green
- `&c` = Red
- `&e` = Yellow
- `&b` = Aqua
- And more...

## 🗂️ File Structure

```
plugins/EnhancedCompass/
├── config.yml                 # Main configuration
├── global-locations.yml       # Global waypoints
└── player-locations/          # Player-specific locations
    ├── uuid1.yml
    ├── uuid2.yml
    └── ...
```

## 🔒 Permissions

| Permission            | Description              | Default |
| --------------------- | ------------------------ | ------- |
| `enhancedcompass.use` | Use all compass commands | `true`  |

## 🛠️ Development

### Building from Source

```bash
git clone https://github.com/yourusername/EnhancedCompass.git
cd EnhancedCompass
./gradlew build
```

The compiled JAR will be in `build/libs/`.

### Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── br/com/fuzalabs/enhancedcompass/
│   │       ├── EnhancedCompass.java
│   │       ├── commands/
│   │       │   ├── CompassCommand.java
│   │       │   └── CompassTabCompleter.java
│   │       └── storage/
│   │           └── LocationStorage.java
│   └── resources/
│       ├── config.yml
│       └── plugin.yml
```

## 🐛 Troubleshooting

### Common Issues

**Compass not working?**

- Make sure you're holding a compass in your main hand
- Check that you have the `enhancedcompass.use` permission

**Location not found?**

- Verify the location name is spelled correctly
- Check if it's a personal or global location

**Player not found?**

- Ensure the player is online
- Make sure you're in the same world

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📞 Support

- 🐛 **Issues**: [GitHub Issues](https://github.com/yourusername/EnhancedCompass/issues)
- 💬 **Discord**: Join our server for help
- 📧 **Email**: support@fuzalabs.com

## 🙏 Acknowledgments

- Built with ❤️ for the Minecraft community
- Powered by the Bukkit API
- Created by **FuzaLabs**

---

<p align="center">
  <strong>Made with 🧭 by <a href="https://fuzalabs.com">FuzaLabs</a></strong>
</p>rincipal é direcionar a bussola do jogador para um local especifico, salvo previamente ou para um jogador.

- Suporte da 1.8 a 1.21
- Mensagem de erro caso o alvo não esteja no mesmo mapa que o player
- Jogador precisa ter uma bussola na mão para usar o comando
- Comando configurável (default: /bussola <jogador|local|coordenadas>)
- Comando /bussola set <name> -> salva um local na base do jogador
- Comando /bussola set global <name> -> salva um local na base global, disponível para todos os jogadores
- Todas as mensagens configuráveis no .yml
- Main: br.com.fuzalabs.enhancedcompass.EnhancedCompass
