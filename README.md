<div align="center">

# 🚀 LCARS Launcher for Android

### A Futuristic, Touch-Optimized Android Launcher Inspired by LCARS Design

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://android.com)
[![API](https://img.shields.io/badge/API-29%2B-brightgreen.svg)](https://android-arsenal.com/api?level=29)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-blue.svg)](https://kotlinlang.org)
[![License](https://img.shields.io/badge/License-Personal%20Use-orange.svg)](#license)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Latest-4285F4.svg)](https://developer.android.com/jetpack/compose)

[Features](#-features) • [Screenshots](#-screenshots) • [Installation](#-installation) • [Usage](#-usage) • [Tech Stack](#-tech-stack) • [Roadmap](#-roadmap)

</div>

---

## 📖 About

**LCARS Launcher** is a fully functional Android home screen launcher that completely replaces your stock launcher with a futuristic, touch-optimized interface. Built from the ground up with **Kotlin** and **Jetpack Compose**, it brings the iconic LCARS (Library Computer Access/Retrieval System) aesthetic to your Android device with smooth animations, multiple color profiles, and a clean architecture.

> ⚠️ **FOR PERSONAL USE ONLY** - This launcher is a personal project and does NOT include any copyrighted Star Trek assets. The visual design is inspired by the LCARS aesthetic but uses generic geometric shapes, colors, and layouts.

---

## ✨ Features

### 🎯 Core Launcher Functionality
- ✅ **Full HOME/LAUNCHER replacement** - Complete Android launcher implementation
- ✅ **App drawer** with all installed applications
- ✅ **Real-time app launching** with smooth transitions
- ✅ **Search functionality** for quick app access
- ✅ **Touch-optimized interface** designed for modern devices

### 🎨 LCARS Visual System
- ✅ **Authentic LCARS color palettes** - Bridge, Engineering, Tactical, Red Alert, Night
- ✅ **Characteristic rounded rectangular panels** with asymmetric designs
- ✅ **All-caps typography** with generous letter spacing
- ✅ **Vertical status rails** on left and right
- ✅ **Smooth animations and transitions** throughout the interface

### 🌈 Multiple Profiles & Modes
| Profile | Theme | Description |
|---------|-------|-------------|
| **🌉 Bridge** | Classic buff and orange | Traditional LCARS colors |
| **⚙️ Engineering** | Yellow and orange | Engineering-focused theme |
| **🎯 Tactical** | Blue and cyan | Tactical operations theme |
| **🚨 Red Alert** | High-contrast red | Emergency alert mode |
| **🌙 Night** | Dimmed palette | Low-light optimized |

### 🏠 Home Deck Layout
- **Left Status Rail:** Real-time clock, date, system status
- **Center Deck Area:** Favorites grid, large action panels, deck controls
- **Right Quick Actions Rail:** Settings, WiFi, Volume, Power
- **Profile Switcher:** Quick access at bottom of screen

### 💾 Data Persistence
- ✅ **Room database** for decks, profiles, and configurations
- ✅ **DataStore** for user preferences
- ✅ **Multiple deck layouts** support (extensible architecture)
- ✅ **Future-ready** for gesture and voice command mappings

---

## 📸 Screenshots

> *Screenshots coming soon! The launcher features:*
> - Home screen with LCARS panels and rails
> - App drawer with search functionality
> - Multiple color profiles (Bridge, Engineering, Tactical, etc.)
> - Touch-optimized interface with smooth animations

---

## 🛠️ Tech Stack

<div align="center">

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Latest-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Room](https://img.shields.io/badge/Room-Database-green?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/training/data-storage/room)
[![Hilt](https://img.shields.io/badge/Hilt-DI-orange?style=for-the-badge&logo=android&logoColor=white)](https://dagger.dev/hilt/)
[![Coroutines](https://img.shields.io/badge/Coroutines-Async-purple?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/docs/coroutines-overview.html)

</div>

### Core Dependencies
- **Jetpack Compose** - Modern declarative UI framework
- **Room** - Local database for data persistence
- **DataStore** - Type-safe preferences storage
- **Hilt** - Dependency injection framework
- **Kotlin Coroutines** - Asynchronous programming
- **Flow** - Reactive data streams

### Architecture Pattern
Clean architecture with clear separation of concerns:
- **Data Layer** - Room database, repositories, data sources
- **Domain Layer** - Business logic embedded in ViewModels
- **UI Layer** - Jetpack Compose, ViewModels, theme system

---

## 📦 Installation

### Requirements
- **Android Studio** Hedgehog (2023.1.1) or later
- **Android SDK** 29+ (Android 10+)
- **Kotlin** 1.9.20+
- **Gradle** 8.2+

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/Snapwave333/l.c.a.r.slauncher.git
   cd l.c.a.r.slauncher
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. **Sync Gradle files**
   - Android Studio will automatically prompt to sync
   - Wait for dependencies to download

4. **Build the project**
   ```bash
   ./gradlew assembleDebug
   ```

5. **Install on device**
   ```bash
   ./gradlew installDebug
   ```

   Or simply click the **Run** button ▶️ in Android Studio

### Setting as Default Launcher

**Method 1: Via Home Button**
1. After installation, press the **Home** button
2. Android will show a launcher picker dialog
3. Select **"LCARS Launcher"**
4. Choose **"Always"** to set as default

**Method 2: Via Settings**
1. Open **Settings** → **Apps** → **Default apps** → **Home app**
2. Select **"LCARS Launcher"**

---

## 🎮 Usage

### Home Screen Overview

The home screen is divided into three main functional areas:

#### 🔷 Left Rail (Status)
- **Time Display** - 24-hour format clock
- **Date Display** - Current date
- **System Indicators** - Status information

#### 🔶 Center Deck
- **Deck Header** - Shows current deck number and profile
- **APPS Button** - Opens the full app drawer (large, prominent)
- **FAVORITES** - Grid of pinned apps (first 8 apps)
- **Profile Switcher** - Quick access buttons at bottom

#### 🔷 Right Rail (Quick Actions)
- **Settings** - System and launcher settings
- **WiFi** - Network controls
- **Volume** - Audio controls
- **Power/Battery** - Power management

### Opening Apps

1. **From Favorites:** Tap any app in the favorites grid on the home screen
2. **From App Drawer:**
   - Tap the large **"APPS"** button
   - Browse or search for your app
   - Tap to launch
   - Use the close button to return home

### App Drawer Features

- 🔍 **Search Bar** - Filter apps by name at the top
- 📱 **Complete App List** - All installed applications
- 🔢 **App Counter** - Shows total number of apps
- 🏷️ **Category Tags** - Organized app information
- ❌ **Close Button** - Return to home screen

### Switching Profiles

Tap any profile button at the bottom of the home screen:
- **BRIDGE** - Classic LCARS colors (buff and orange)
- **ENG** - Engineering yellow/orange theme
- **TAC** - Tactical blue/cyan theme
- **ALERT** - Red alert high-contrast mode

The entire interface will smoothly transition to the new color scheme!

---

## 📁 Project Structure

```
app/src/main/java/com/lcars/launcher/
├── 📂 data/                       # Data layer
│   ├── 📂 local/                  # Room database
│   │   ├── 📂 entities/          # Database entities
│   │   ├── 📂 dao/               # Data Access Objects
│   │   └── LcarsDatabase.kt      # Database configuration
│   ├── 📂 models/                # Data models
│   ├── 📂 preferences/           # DataStore preferences
│   └── 📂 repository/            # Repository pattern
│
├── 📂 di/                        # Dependency injection (Hilt)
│
├── 📂 ui/                        # UI layer
│   ├── 📂 theme/                 # LCARS theming system
│   │   ├── LcarsPalette.kt      # Color palettes
│   │   ├── LcarsTypography.kt   # Typography definitions
│   │   ├── LcarsShapes.kt       # Shape definitions
│   │   └── LcarsTheme.kt        # Theme provider
│   │
│   ├── 📂 components/            # Reusable LCARS components
│   │   ├── LcarsPanel.kt        # Core panel component
│   │   ├── LcarsRail.kt         # Vertical status rails
│   │   ├── LcarsAppIcon.kt      # App icon styling
│   │   └── LcarsAlertBanner.kt  # Alert banner component
│   │
│   ├── 📂 home/                  # Home screen module
│   │   ├── LcarsHomeActivity.kt
│   │   ├── LcarsHomeViewModel.kt
│   │   ├── LcarsHomeScreen.kt
│   │   └── 📂 components/        # Home-specific components
│   │
│   ├── 📂 drawer/                # App drawer module
│   │   └── AppDrawer.kt
│   │
│   └── 📂 settings/              # Settings module
│       └── SettingsActivity.kt
│
└── LcarsLauncherApp.kt           # Application class
```

---

## 🎨 Customization

### Adding Custom Sounds

To add LCARS-style UI sounds:

1. Create the directory:
   ```bash
   mkdir -p app/src/main/assets/lcars_sounds/
   ```

2. Add your sound files:
   - Must be legally owned or from free/open sources
   - Should be short (< 1 second) for button taps
   - Use MP3 or OGG format

> **Note:** Sound playback infrastructure is prepared but not yet fully implemented.

### Modifying Color Palettes

Edit `app/src/main/java/com/lcars/launcher/ui/theme/LcarsPalette.kt`:

```kotlin
val MyCustomPalette = LcarsPalette(
    background = Color(0xFF000000),
    panelPrimary = Color(0xFFFFCC99),
    panelSecondary = Color(0xFFCC99CC),
    panelTertiary = Color(0xFF9999CC),
    text = Color(0xFFFFFFFF),
    textDim = Color(0xFF999999),
    accent = Color(0xFFFF9966),
    alert = Color(0xFFFF0000)
)
```

### Adding New Profiles

1. **Define a new palette** in `LcarsPalettes.kt`
2. **Add an enum value** to `LcarsPaletteType`
3. **Add a profile button** in the deck view
4. **Update ViewModel** to handle the new profile

---

## 🗺️ Roadmap

### 🎯 Planned Features

- [ ] **Swipe gestures** for deck navigation
- [ ] **Widget support** and integration
- [ ] **Custom panel configurations** per deck
- [ ] **Sound and haptic feedback** system
- [ ] **Voice command system** ("Computer, open...")
- [ ] **Multiple deck support** with swipe navigation
- [ ] **App favorites management** UI
- [ ] **Custom shortcuts** and mission presets
- [ ] **Immersive mode** toggles
- [ ] **Performance mode** for low-end devices
- [ ] **Backup and restore** configurations

### 💡 Possible Extensions

- 🌟 Stardate calculations and display
- 📊 System resource monitoring panels
- 🌤️ Weather widget integration
- 📅 Calendar/agenda panels
- 🔤 Custom fonts support
- ⚡ Animation speed controls
- ♿ Enhanced accessibility features
- 🔔 Notification integration
- 🖼️ Live wallpaper support
- 🎭 More theme variations

---

## 🐛 Troubleshooting

### Launcher doesn't appear as option
- ✓ Ensure the app is successfully installed
- ✓ Verify HOME intent filters in `AndroidManifest.xml`
- ✓ Restart the device
- ✓ Clear defaults for current launcher in Settings

### Apps don't launch
- ✓ Verify `QUERY_ALL_PACKAGES` permission in manifest
- ✓ Check that package names are correct
- ✓ Note: Some system apps may not be launchable by third-party launchers

### UI performance issues
- ✓ Enable performance mode (when implemented)
- ✓ Reduce animation scale in Developer Options
- ✓ Close unnecessary background apps
- ✓ Ensure device meets minimum requirements (Android 10+)

### Colors don't change when switching profiles
- ✓ Ensure the app has been rebuilt with latest code
- ✓ Check ViewModel is properly updating state
- ✓ Verify theme provider is receiving profile updates

---

## 🤝 Contributing

This is a personal project, but contributions are welcome!

### How to Contribute

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit your changes** (`git commit -m 'Add some AmazingFeature'`)
4. **Push to the branch** (`git push origin feature/AmazingFeature`)
5. **Open a Pull Request**

### Contribution Guidelines

- Follow Kotlin coding conventions
- Use meaningful commit messages
- Add comments for complex logic
- Test on multiple Android versions if possible
- Ensure no copyrighted assets are included
- Update documentation for new features

### Areas for Contribution

- 🎨 New color palettes and themes
- 🔧 Performance optimizations
- 🐛 Bug fixes and stability improvements
- 📱 New features from the roadmap
- 📚 Documentation improvements
- ♿ Accessibility enhancements

---

## 📄 License

This project is for **PERSONAL USE ONLY**.

The code is provided as-is without warranty. You are free to fork and modify for your own personal use. The LCARS visual design is inspired by the aesthetic seen in Star Trek, but this implementation uses generic geometric designs and does not include any copyrighted assets.

### Important Legal Notes

- **No Commercial Use** - This project is not licensed for commercial use
- **No Warranty** - Provided as-is without any guarantees
- **Asset Responsibility** - Users must supply their own sound files and custom assets
- **No Trademark Violation** - Does not use copyrighted Star Trek assets

---

## ⚠️ Disclaimer

This launcher is a **fan project** and is not affiliated with, endorsed by, or connected to CBS, Paramount, or any Star Trek rights holders.

**LCARS** and **Star Trek** are trademarks of CBS Studios Inc.

This project is created purely for educational and personal use purposes, celebrating the iconic design aesthetic through original implementation.

---

## 🙏 Acknowledgments

- 🎨 Inspired by the **LCARS interface design aesthetic**
- 🛠️ Built with **modern Android development tools and best practices**
- 📚 Powered by **open-source libraries and frameworks**
- 💡 Thanks to the Android and Kotlin communities
- 🌟 Special thanks to all contributors and users

---

## 📞 Support

If you encounter any issues or have questions:

1. **Check the [Troubleshooting](#-troubleshooting) section**
2. **Review existing [Issues](../../issues)** on GitHub
3. **Open a new issue** with detailed information:
   - Device model and Android version
   - Steps to reproduce the problem
   - Expected vs actual behavior
   - Screenshots if applicable

---

## 📊 Project Stats

**Version:** 1.0.0
**Min SDK:** 29 (Android 10)
**Target SDK:** 34 (Android 14)
**Language:** Kotlin
**UI Framework:** Jetpack Compose
**Architecture:** Clean Architecture with MVVM

---

<div align="center">

**Made with ❤️ for Android and LCARS enthusiasts**

⭐ **Star this repo** if you find it useful!

[Back to Top](#-lcars-launcher-for-android)

</div>
