# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.1.0] - 2025-07-23

### Added

- Initial release of EnhancedCompass
- Point compass to online players
- Save and navigate to personal locations
- Save and navigate to global server locations
- Point compass to specific coordinates
- Reset compass to world spawn
- Configurable messages with color support
- Tab completion for all commands
- Persistent data storage
- Multi-world support with validation
- Permission system integration

### Features

- `/compass <player>` - Point to online players
- `/compass <location>` - Point to saved locations
- `/compass <x> <y> <z>` - Point to coordinates
- `/compass set <name>` - Save personal locations
- `/compass set global <name>` - Save global locations
- `/compass reset` - Reset to world spawn

### Technical

- Built for Minecraft 1.13+
- Compatible with Bukkit, Spigot, and Paper
- Clean code architecture with separated concerns
- Comprehensive error handling
- Optimized file storage system
