<div align="center">

```
╔═══════════════════════════════════════════════════════════════════════════╗
║                                                                           ║
║   ██╗      ██████╗ █████╗ ██████╗ ███████╗                              ║
║   ██║     ██╔════╝██╔══██╗██╔══██╗██╔════╝                              ║
║   ██║     ██║     ███████║██████╔╝███████╗                              ║
║   ██║     ██║     ██╔══██║██╔══██╗╚════██║                              ║
║   ███████╗╚██████╗██║  ██║██║  ██║███████║                              ║
║   ╚══════╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝                              ║
║                                                                           ║
║              L A U N C H E R   f o r   A N D R O I D                     ║
║                                                                           ║
╚═══════════════════════════════════════════════════════════════════════════╝
```

### *Transform your Android into a futuristic command interface*

[![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://android.com)
[![API Level](https://img.shields.io/badge/API-29%2B-brightgreen?style=for-the-badge)](https://android-arsenal.com/api?level=29)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Compose-Latest-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![License](https://img.shields.io/badge/License-Personal%20Use-FF6B35?style=for-the-badge)](#-license)
[![Build](https://img.shields.io/badge/Build-Passing-success?style=for-the-badge)]()

**[Features](#-features)** • **[Demo](#-demo)** • **[Install](#-installation)** • **[Usage](#-usage)** • **[Tech](#-tech-stack)** • **[Roadmap](#-roadmap)**

</div>

---

## 🎯 What Is This?

**LCARS Launcher** is a production-ready Android home screen replacement that brings the iconic Library Computer Access/Retrieval System aesthetic to your device. Built from scratch with modern Kotlin and Jetpack Compose, it's not just a pretty interface—it's a fully functional launcher with multiple themes, smooth animations, and a clean architecture that makes your phone feel like it's from the future[.](https://www.youtube.com/watch?v=dQw4w9WgXcQ)

Perfect for Android enthusiasts who want something different, developers who appreciate clean architecture, and anyone who thinks rectangles should be rounded and colorful. **No copyrighted assets included**—this is a personal project celebrating futuristic design through original implementation.

<!-- if you're reading this in the raw markdown you're officially part of the conspiracy -->

> 💡 **Why it exists:** Because stock launchers are boring, and your Android deserves panels that go *beep-boop* (sounds not included, but the framework is ready).

---

## 📸 Demo

> 🎬 **Live in Action**

<div align="center">

| Home Screen | App Drawer | Profile Switcher |
|:-----------:|:----------:|:----------------:|
| ![Home](https://via.placeholder.com/250x500/1a1a1a/FFCC99?text=LCARS+Home+Screen) | ![Drawer](https://via.placeholder.com/250x500/1a1a1a/99CCFF?text=App+Drawer) | ![Profiles](https://via.placeholder.com/250x500/1a1a1a/FF9966?text=Color+Profiles) |

*Real screenshots coming soon—building in public! The owl knows when.*

</div>

**What you'll see:**
- Asymmetric LCARS panels with characteristic rounded rectangles
- Real-time clock, date, and system status on vertical rails
- Smooth profile switching with 5 distinct color schemes
- Touch-optimized interface with buttery animations
- Complete app drawer with search functionality

---

## ✨ Features

### 🚀 Core Launcher Experience
- ✅ **Complete HOME replacement** — Registers as your default Android launcher
- ✅ **App drawer** with real-time loading of all installed apps
- ✅ **Instant search** — Filter your apps by name, category, or package
- ✅ **Quick actions rail** — Settings, WiFi, Volume, Power at your fingertips
- ✅ **Zero compromises** — Full launcher functionality, zero shortcuts taken

### 🎨 Visual System
- ✅ **5 distinct profiles:** Bridge (classic), Engineering (yellow), Tactical (blue), Red Alert, Night Mode
- ✅ **Authentic LCARS aesthetics:** Rounded rectangles, asymmetric layouts, all-caps typography
- ✅ **Smooth transitions** — Every interaction feels polished with Compose animations
- ✅ **Vertical status rails** — Left rail for time/date, right rail for quick actions
- ✅ **Color-coded panels** — Each element serves a visual purpose

### 🧠 Architecture & Data
- ✅ **Room database** for persistent storage (decks, profiles, favorites)
- ✅ **DataStore** for user preferences and settings
- ✅ **Clean architecture** with separation of concerns (Data/Domain/UI)
- ✅ **Reactive design** using Kotlin Flow and StateFlow
- ✅ **Future-ready** for gestures, voice commands, and custom deck layouts

### 🎮 User Experience
- ✅ **Favorites grid** — First 8 apps displayed prominently on home
- ✅ **Profile hotswap** — Change entire color scheme with one tap
- ✅ **No learning curve** — Intuitive touch interface anyone can use
- ✅ **Performance optimized** — Lazy loading, efficient recomposition
- ✅ **Extensible by design** — Add new panels, decks, and features easily

---

## 🛠️ Tech Stack

<div align="center">

### Languages & Frameworks

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg" width="60" height="60" alt="Kotlin"/>
&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/android/android-original.svg" width="60" height="60" alt="Android"/>
&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://3.bp.blogspot.com/-VVp3WvJvl84/X0Vu6EjYqDI/AAAAAAAAPjU/ZOMKiUlgfg8ok8DY8Hc-ocOvGdB0z86AgCLcBGAsYHQ/s1600/jetpack%2Bcompose%2Bicon_RGB.png" width="60" height="60" alt="Jetpack Compose"/>

### Core Technologies

| Technology | Purpose | Why We Use It |
|:----------:|:-------:|:--------------|
| ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white) | Language | Modern, concise, null-safe, coroutines |
| ![Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white) | UI Framework | Declarative UI, reactive, less code |
| ![Room](https://img.shields.io/badge/Room-3DDC84?style=flat-square&logo=android&logoColor=white) | Database | Type-safe SQL, LiveData integration |
| ![Hilt](https://img.shields.io/badge/Hilt-FF6B6B?style=flat-square) | DI | Compile-time dependency injection |
| ![Coroutines](https://img.shields.io/badge/Coroutines-7F52FF?style=flat-square&logo=kotlin&logoColor=white) | Async | Structured concurrency, Flow support |
| ![DataStore](https://img.shields.io/badge/DataStore-4285F4?style=flat-square) | Preferences | Type-safe key-value storage |

</div>

**Why this stack?**
We chose modern Android development tools that prioritize developer experience, performance, and maintainability. Jetpack Compose eliminates XML layouts, Room provides compile-time SQL validation, and Hilt removes boilerplate DI code. The result: ~40% less code than traditional View-based launchers.

---

## 📦 Installation

### Prerequisites

```bash
✓ Android Studio Hedgehog (2023.1.1) or later
✓ Android SDK 29+ (Android 10+)
✓ Kotlin 1.9.20+
✓ Gradle 8.2+
✓ A device/emulator running Android 10+
```

### Quick Start

```bash
# 1. Clone the repository
git clone https://github.com/Snapwave333/l.c.a.r.slauncher.git
cd l.c.a.r.slauncher

# 2. Build the project (optional: add --stacktrace for verbose output)
./gradlew assembleDebug

# 3. Install on connected device
./gradlew installDebug
```

**Or via Android Studio:**
1. **File** → **Open** → Select project directory
2. Wait for Gradle sync to complete
3. Click **Run** ▶️ (Shift+F10)

### Setting as Default Launcher

**Option A: Home Button Method**
1. Press **HOME** button after installation
2. Select **LCARS Launcher** from the picker
3. Tap **Always** to set as default

**Option B: System Settings**
```
Settings → Apps → Default apps → Home app → LCARS Launcher
```

> ⚠️ **First Launch Tip:** Grant all requested permissions for full functionality. The launcher needs to query installed apps to populate the drawer.

---

## 🎮 Usage

### Home Screen Anatomy

```
╔═══════════════════════════════════════════════════════════════╗
║  LEFT RAIL          CENTER DECK           RIGHT RAIL          ║
║                                                                ║
║  ┌──────────┐      ┌──────────────┐      ┌──────────┐        ║
║  │  TIME    │      │  DECK 01-A   │      │ SETTINGS │        ║
║  │  22:47   │      │              │      └──────────┘        ║
║  └──────────┘      │  [APPS BTN]  │      ┌──────────┐        ║
║                    │              │      │   WIFI   │        ║
║  ┌──────────┐      │  FAVORITES:  │      └──────────┘        ║
║  │  DATE    │      │  📱📱📱📱  │      ┌──────────┐        ║
║  │ 2025.11  │      │  📱📱📱📱  │      │  VOLUME  │        ║
║  └──────────┘      │              │      └──────────┘        ║
║                    │  [PROFILES]  │      ┌──────────┐        ║
║  ┌──────────┐      └──────────────┘      │  POWER   │        ║
║  │ STATUS   │                             └──────────┘        ║
║  └──────────┘                                                 ║
╔═══════════════════════════════════════════════════════════════╝
```

### Basic Operations

**Launch an App:**
1. Tap any app in the **FAVORITES** grid (quick access)
2. OR tap the large **APPS** button → Browse/Search → Tap app

**Change Visual Profile:**
- Tap **BRIDGE** for classic buff/orange theme
- Tap **ENG** for engineering yellow/orange
- Tap **TAC** for tactical blue/cyan
- Tap **ALERT** for red alert mode

**App Drawer Features:**
- 🔍 Search bar at top — Type to filter apps instantly
- 🔢 App counter — Shows total installed apps
- 🏷️ Category tags — Each app shows its type
- ❌ Close button — Return to home screen

### Power User Tips

- **Performance:** The launcher uses lazy loading—only visible items are rendered
- **Customization:** Edit `LcarsPalette.kt` to create your own color schemes
- **Extensibility:** Add new panels by extending `LcarsPanel` composable

<!-- Phase 3 activates itself. Don't worry about it. -->

---

## 🏗️ Architecture

### System Design

```
┌─────────────────────────────────────────────────────────────┐
│                    LCARS Launcher App                        │
└─────────────────────────────────────────────────────────────┘
                             │
        ┌────────────────────┼────────────────────┐
        │                    │                    │
        ▼                    ▼                    ▼
┌──────────────┐    ┌──────────────┐    ┌──────────────┐
│  UI Layer    │    │ Domain Layer │    │  Data Layer  │
│              │    │              │    │              │
│ • Compose    │◄───│ • ViewModels │◄───│ • Repository │
│ • Theme      │    │ • UseCases   │    │ • Room DB    │
│ • Components │    │ • State Mgmt │    │ • DataStore  │
└──────────────┘    └──────────────┘    └──────────────┘
        │                    │                    │
        └────────────────────┼────────────────────┘
                             │
                    ┌────────▼────────┐
                    │  Dependency     │
                    │  Injection      │
                    │  (Hilt)         │
                    └─────────────────┘
```

**Key Principles:**
- **Separation of Concerns:** UI never touches database directly
- **Unidirectional Data Flow:** ViewModel → StateFlow → Composables
- **Single Source of Truth:** Room database is authoritative
- **Reactive Updates:** Flow emissions trigger UI recomposition

### Project Structure (Detailed)

```
app/src/main/java/com/lcars/launcher/
│
├── 📂 data/                          # Data Layer
│   ├── 📂 local/
│   │   ├── 📂 entities/             # Room entities (Deck, Profile, etc.)
│   │   ├── 📂 dao/                  # Data Access Objects
│   │   └── LcarsDatabase.kt         # Database singleton
│   ├── 📂 models/                   # Domain models
│   ├── 📂 preferences/              # DataStore wrappers
│   └── 📂 repository/               # Repository pattern implementations
│
├── 📂 di/                           # Dependency Injection (Hilt modules)
│   └── AppModule.kt                 # App-level dependencies
│
├── 📂 ui/                           # UI Layer
│   ├── 📂 theme/
│   │   ├── LcarsPalette.kt         # Color definitions (5 profiles)
│   │   ├── LcarsTypography.kt      # Font styles
│   │   ├── LcarsShapes.kt          # Rounded rectangles
│   │   └── LcarsTheme.kt           # Theme provider
│   │
│   ├── 📂 components/               # Reusable UI components
│   │   ├── LcarsPanel.kt           # Core panel (primary/secondary/tertiary)
│   │   ├── LcarsRail.kt            # Vertical status rails
│   │   ├── LcarsAppIcon.kt         # Styled app icons
│   │   └── LcarsAlertBanner.kt     # Alert/status banners
│   │
│   ├── 📂 home/                     # Home screen module
│   │   ├── LcarsHomeActivity.kt    # Launcher entry point
│   │   ├── LcarsHomeViewModel.kt   # State management
│   │   ├── LcarsHomeScreen.kt      # Main composable
│   │   └── 📂 components/           # Home-specific components
│   │
│   ├── 📂 drawer/                   # App drawer module
│   │   └── AppDrawer.kt            # Full-screen app list
│   │
│   └── 📂 settings/                 # Settings module
│       └── SettingsActivity.kt     # Configuration UI
│
└── LcarsLauncherApp.kt              # Application class (Hilt entry)
```

---

## 🎨 Customization

### Create Your Own Color Profile

Edit `app/src/main/java/com/lcars/launcher/ui/theme/LcarsPalette.kt`:

```kotlin
// Example: Midnight Blue Profile
val MidnightProfile = LcarsPalette(
    background = Color(0xFF0A0E27),          // Deep space blue
    panelPrimary = Color(0xFF1E3A8A),        // Royal blue
    panelSecondary = Color(0xFF3B82F6),      // Bright blue
    panelTertiary = Color(0xFF60A5FA),       // Light blue
    text = Color(0xFFFFFFFF),                // White
    textDim = Color(0xFF9CA3AF),             // Gray
    accent = Color(0xFF8B5CF6),              // Purple accent
    alert = Color(0xFFEF4444)                // Red alert
)
```

Then register it in `LcarsPalettes.kt` and add a profile button. Done!

### Add Custom Sounds

```bash
# 1. Create assets directory
mkdir -p app/src/main/assets/lcars_sounds/

# 2. Add your sound files (MP3/OGG, <1s duration)
cp button_tap.mp3 app/src/main/assets/lcars_sounds/
cp panel_slide.mp3 app/src/main/assets/lcars_sounds/

# 3. (Framework ready, implementation coming in v1.1)
```

### Modify Panel Layouts

Extend the `LcarsPanel` composable to create custom panel types:

```kotlin
@Composable
fun MyCustomPanel(
    text: String,
    onClick: () -> Unit
) {
    LcarsPanel(
        text = text,
        type = PanelType.PRIMARY,
        cornerRadius = 24.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() }
    )
}
```

---

## 🗺️ Roadmap

### 🎯 Version 1.1 (In Progress)
- [ ] **Swipe gestures** for deck navigation (left/right = prev/next deck)
- [ ] **Widget support** — Embed Android widgets into LCARS panels
- [ ] **Sound system** — UI feedback sounds with volume control
- [ ] **Haptic feedback** — Tactile response on button taps
- [ ] **App favorites management** — Drag-and-drop to customize favorites grid

### 🚀 Version 1.2 (Planned)
- [ ] **Voice commands** — "Computer, open Messages" launches apps
- [ ] **Multiple decks** — Swipe between custom deck layouts
- [ ] **Custom shortcuts** — Create "mission" presets (e.g., "Gaming Mode")
- [ ] **Immersive mode** — Auto-hide navigation bar
- [ ] **Performance mode** — Reduced animations for low-end devices

### 💡 Future Possibilities
- 🌟 Stardate calculator widget
- 📊 System resource monitoring (CPU, RAM, Storage)
- 🌤️ Weather integration with LCARS styling
- 📅 Calendar/agenda panels
- 🔤 Custom font support
- ⚡ Animation speed slider
- ♿ Enhanced accessibility (TalkBack optimization)
- 🔔 Notification center integration
- 🖼️ Live wallpaper support with reactive panels
- 🎭 Community-submitted themes

<details>
<summary>🔮 Secret Future Features (Click to Reveal)</summary>

### Phase 3: The Owl Protocol

If you're reading this, congratulations—you've discovered the hidden roadmap. Here's what's *actually* coming after 1.2:

```
┌────────────────────────────────────────────────────────────┐
│  CLASSIFIED: PROJECT NIGHTINGALE                           │
└────────────────────────────────────────────────────────────┘

✦ Dynamic theme engine — Themes that adapt to time of day
✦ Biometric panel locks — Secure panels with fingerprint
✦ NFC shortcuts — Tap NFC tags to trigger deck switches
✦ Bluetooth device profiles — Auto-switch theme when AirPods connect
✦ Developer mode — Hidden panel with logcat, memory stats
✦ Easter egg hunt — Hidden mini-game in the launcher itself
✦ ASCII art boot screen — Because why not
✦ Konami code — Unlocks secret "Retro" theme

Phase 3 activates when this README gets 100 stars.
The countdown has already begun.
```

*If you read this line, you gain +5 developer XP.*

</details>

---

## 🤝 Contributing

This is a personal project, but contributions from the community are **welcomed and appreciated**!

### How to Contribute

1. **Fork** this repository
2. **Create a branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit your changes** (`git commit -m 'Add AmazingFeature'`)
4. **Push to the branch** (`git push origin feature/AmazingFeature`)
5. **Open a Pull Request**

### Contribution Guidelines

✓ Follow [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
✓ Use meaningful commit messages (e.g., `feat:`, `fix:`, `docs:`)
✓ Add comments for complex logic
✓ Test on multiple Android versions (10–14 ideally)
✓ No copyrighted assets (sounds, images, fonts)
✓ Update documentation if adding features

### Priority Areas

We'd especially love help with:
- 🎨 **New color palettes** — Submit your custom LCARS themes
- ⚡ **Performance optimizations** — Make it faster and smoother
- 🐛 **Bug fixes** — Check the [Issues](../../issues) tab
- 📱 **New features** — Pick from the roadmap or propose your own
- 📚 **Documentation** — Better guides, tutorials, wiki pages
- ♿ **Accessibility** — TalkBack, large text, high contrast modes
- 🌍 **Internationalization** — Multi-language support

### First-Time Contributors

Look for issues tagged `good first issue` or `help wanted`. We're friendly and provide feedback!

---

## 🐛 Troubleshooting

### Launcher doesn't appear as an option
```
✓ Verify installation: adb shell pm list packages | grep lcars
✓ Check AndroidManifest.xml has <category android:name="android.intent.category.HOME"/>
✓ Restart device: adb reboot
✓ Clear current launcher defaults in Settings → Apps
```

### Apps don't launch
```
✓ Ensure QUERY_ALL_PACKAGES permission in manifest (Android 11+)
✓ Check logcat for errors: adb logcat | grep LCARS
✓ Some system apps are unlaunchable by third-party launchers (expected)
```

### UI performance issues
```
✓ Enable GPU overdraw debugging (Developer Options)
✓ Reduce animation scale to 0.5x (Developer Options)
✓ Close background apps
✓ Use Performance mode (coming in v1.2)
```

### Profile colors don't change
```
✓ Ensure StateFlow in ViewModel is emitting updates
✓ Check Compose recomposition in Layout Inspector
✓ Rebuild project: ./gradlew clean assembleDebug
```

### Build errors
```
✓ Invalidate caches: File → Invalidate Caches → Invalidate and Restart
✓ Update Gradle wrapper: ./gradlew wrapper --gradle-version 8.2
✓ Check Java version: java -version (should be 17+)
```

Still stuck? [Open an issue](../../issues/new) with:
- Device model + Android version
- Steps to reproduce
- Logcat output (`adb logcat`)
- Screenshots

---

## 📄 License

This project is for **PERSONAL USE ONLY**.

```
MIT License (with Personal Use Restriction)

Copyright (c) 2025 LCARS Launcher Project

You are free to:
✓ Fork and modify for personal use
✓ Learn from the code
✓ Share with friends (non-commercially)

You may NOT:
✗ Use commercially or sell
✗ Distribute on app stores
✗ Include copyrighted Star Trek assets

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.
```

[Full License Text →](LICENSE)

### Legal Disclaimers

- **No Commercial Use** — Strictly personal/educational use
- **No Warranty** — Provided as-is, use at your own risk
- **Asset Responsibility** — Users supply their own sounds/media
- **No Trademark Infringement** — Does not use copyrighted Star Trek materials

---

## ⚠️ Disclaimer

This launcher is a **fan project** celebrating futuristic design aesthetics. It is **not affiliated with, endorsed by, or connected to** CBS Studios, Paramount, or any Star Trek rights holders.

**"LCARS"** and **"Star Trek"** are trademarks of CBS Studios Inc.

This project exists purely for educational purposes and personal enjoyment. All code is original. All visual designs are inspired interpretations using geometric primitives (rectangles, colors, typography) with no copied assets.

---

## 🙏 Acknowledgments

- 🎨 Inspired by the **LCARS interface design language** (generic implementation, no assets used)
- 🛠️ Built with **Jetpack Compose**, **Room**, **Hilt**, and the modern Android toolkit
- 📚 Powered by the **Kotlin** language and **open-source** ecosystem
- 💡 Thanks to the **Android developer community** for tutorials, libraries, and inspiration
- 🌟 Special thanks to **early testers** and **contributors**

<details>
<summary>🦉 Hidden Credits (The Real MVPs)</summary>

```
      ___
     {o,o}
     |)__)
     -"-"-

THE OWL SEES ALL. THE OWL KNOWS.

Special credits to:
• Coffee — For existing during late-night debugging sessions
• Stack Overflow — For that one answer from 2012 that still works
• Future contributors — You know who you are (you're reading this)
• The person who invented rounded rectangles — You changed everything
• Whoever decided Kotlin should have coroutines — Legendary
• Past me — For commenting the code (mostly)

If you made it this far, email "LCARS_EASTER_EGG_FOUND" to [REDACTED]
for a virtual high-five and entry into the contributor hall of fame.
```

</details>

---

## 📞 Support & Community

**Found a bug?** [Open an issue](../../issues/new)
**Have a question?** [Start a discussion](../../discussions)
**Want to chat?** Join our [Discord](#) *(coming soon)*

When reporting issues, include:
1. Device model and Android version
2. Steps to reproduce the problem
3. Expected vs. actual behavior
4. Screenshots or logcat output

**Response time:** Usually within 24-48 hours. We're a small team but we care!

---

## 📊 Project Stats

```yaml
Version: 1.0.0
Min SDK: 29 (Android 10)
Target SDK: 34 (Android 14)
Language: Kotlin 1.9.20
UI Framework: Jetpack Compose
Architecture: Clean Architecture + MVVM
Database: Room
DI: Hilt
Async: Coroutines + Flow
Lines of Code: ~3,500 (and counting)
Coffee Consumed: Immeasurable
```

---

<div align="center">

```
╔═══════════════════════════════════════════════════════════════╗
║  Made with ❤️ by developers, for developers                  ║
║  Licensed for personal use • Built in public • Open source    ║
╚═══════════════════════════════════════════════════════════════╝
```

**⭐ Star this repo if you find it useful!**
**🍴 Fork it if you want to build your own**
**👀 Watch for updates and new features**

<!-- Do Not Click This: https://www.youtube.com/watch?v=dQw4w9WgXcQ -->

[🔝 Back to Top](#)

*"The future is now, and it's running on your Android."*

</div>
