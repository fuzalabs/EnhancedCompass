# 🧭 EnhancedCompass

**A powerful and intuitive compass plugin for Minecraft that transforms your navigation experience.**

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/java-8+-blue.svg)](https://www.oracle.com/java/)
[![Minecraft](https://img.shields.io/badge/minecraft-1.8--1.20+-green.svg)](https://minecraft.net/)

## ✨ Features

- 🎯 **Point compass to players** - Navigate to any online player instantly
- 📍 **Save custom locations** - Create personal and global waypoints
- 🔢 **Navigate by coordinates** - Jump to any XYZ position
- � **Group-based location limits** - Configurable limits per permission group
- 🌐 **Multi-language support** - Localized messages for your community
- ⚡ **High performance** - Lightweight and optimized for busy servers

## 🚀 Quick Start

### Installation

1. Download the latest release from [Releases](https://github.com/fuzalabs/EnhancedCompass/releases)
2. Drop the JAR file into your `plugins/` directory
3. Restart your server
4. Configure groups and permissions (optional)

### Basic Usage

```
/compass Steve           # Point to player Steve
/compass home           # Point to saved location "home"
/compass 100 64 200     # Point to coordinates
/compass set home       # Save current location
/compass reset          # Reset to world spawn
```

## 📊 Server Compatibility

| Server Type | Version Support |
| ----------- | --------------- |
| **Bukkit**  | ✅ 1.8 - 1.21+  |
| **Spigot**  | ✅ 1.8 - 1.21+  |
| **Paper**   | ✅ 1.8 - 1.21+  |

## 📚 Documentation

For detailed configuration, commands, permissions, and advanced features, visit our [complete documentation](DOCUMENTATION.md).

### Quick Links

- [Commands & Permissions](DOCUMENTATION.md#commands)
- [Configuration Guide](DOCUMENTATION.md#configuration)
- [Group System Setup](DOCUMENTATION.md#group-system)
- [Troubleshooting](DOCUMENTATION.md#troubleshooting)

## 🛠️ Development

### Building from Source

```bash
git clone https://github.com/fuzalabs/EnhancedCompass.git
cd EnhancedCompass
./gradlew build
```

The compiled JAR will be available in `build/libs/`.

## 🤝 Contributing

We welcome contributions! Please read our [Contributing Guidelines](CONTRIBUTING.md) before submitting a PR.

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📞 Support

- 🐛 **Issues**: [GitHub Issues](https://github.com/fuzalabs/EnhancedCompass/issues)
- 📧 **Email**: contato@fuzalabs.com

## � License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<p align="center">
  <strong>Made with 🧭 by <a href="https://fuzalabs.com.br">FuzaLabs</a></strong><br>
  <em>Enhancing Minecraft navigation, one compass at a time.</em>
</p>
