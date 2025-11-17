# LCARS Launcher for Android

A fully functional, LCARS-inspired Android home screen launcher built with Kotlin and Jetpack Compose. This launcher completely replaces your stock Android launcher with a futuristic, touch-optimized interface inspired by the LCARS (Library Computer Access/Retrieval System) design aesthetic.

## ⚠️ Important Notice

**FOR PERSONAL USE ONLY**

This launcher is a personal project and does NOT include any copyrighted Star Trek assets. The visual design is inspired by the LCARS aesthetic but uses generic geometric shapes, colors, and layouts. Users must supply their own sound files and custom assets.

## Features

### Core Launcher Functionality
- ✅ Full HOME/LAUNCHER replacement
- ✅ Registers as default Android launcher
- ✅ Complete app drawer with all installed applications
- ✅ Real-time app launching
- ✅ Search functionality for quick app access
- ✅ Touch-optimized LCARS interface

### LCARS Visual System
- ✅ Authentic LCARS color palettes (Bridge, Engineering, Tactical, Red Alert, Night)
- ✅ Characteristic rounded rectangular panels
- ✅ Asymmetric panel designs
- ✅ All-caps typography with generous letter spacing
- ✅ Vertical status rails (left and right)
- ✅ Smooth animations and transitions

### Profiles & Modes
- ✅ Multiple visual profiles with different color schemes
- ✅ Bridge Mode - Classic buff and orange
- ✅ Engineering Mode - Yellow and orange focused
- ✅ Tactical Mode - Blue and cyan emphasis
- ✅ Red Alert Mode - High-contrast red theme
- ✅ Night Mode - Dimmed palette for low-light use

### Home Deck Layout
- ✅ Left status rail: Time, date, system status
- ✅ Right quick actions rail: Settings, WiFi, Volume, Power
- ✅ Central deck area with favorites grid
- ✅ Large LCARS panels for primary actions
- ✅ Profile switcher at bottom

### Data Persistence
- ✅ Room database for decks, profiles, and configurations
- ✅ DataStore for user preferences
- ✅ Support for multiple deck layouts (extensible)
- ✅ Gesture mappings storage (ready for future implementation)
- ✅ Voice command mappings (ready for future implementation)

## Project Structure

```
app/src/main/java/com/lcars/launcher/
├── data/                       # Data layer
│   ├── local/                  # Room database
│   │   ├── entities/          # Database entities
│   │   ├── dao/               # Data Access Objects
│   │   └── LcarsDatabase.kt   # Database setup
│   ├── models/                # Data models
│   ├── preferences/           # DataStore preferences
│   └── repository/            # Repositories
├── di/                        # Dependency injection (Hilt)
├── ui/                        # UI layer
│   ├── theme/                 # LCARS theming system
│   │   ├── LcarsPalette.kt   # Color palettes
│   │   ├── LcarsTypography.kt # Typography
│   │   ├── LcarsShapes.kt    # Shapes
│   │   └── LcarsTheme.kt     # Theme provider
│   ├── components/            # Reusable LCARS components
│   │   ├── LcarsPanel.kt     # Core panel component
│   │   ├── LcarsRail.kt      # Vertical rails
│   │   ├── LcarsAppIcon.kt   # App icons
│   │   └── LcarsAlertBanner.kt # Alert banners
│   ├── home/                  # Home screen
│   │   ├── LcarsHomeActivity.kt
│   │   ├── LcarsHomeViewModel.kt
│   │   ├── LcarsHomeScreen.kt
│   │   └── components/        # Home screen components
│   ├── drawer/                # App drawer
│   │   └── AppDrawer.kt
│   └── settings/              # Settings
│       └── SettingsActivity.kt
└── LcarsLauncherApp.kt        # Application class
```

## Building & Installation

### Requirements
- Android Studio Hedgehog (2023.1.1) or later
- Android SDK 29+ (Android 10+)
- Kotlin 1.9.20+
- Gradle 8.2+

### Build Steps

1. Clone the repository:
```bash
git clone <repository-url>
cd l.c.a.r.slauncher
```

2. Open the project in Android Studio

3. Sync Gradle files

4. Build the project:
```bash
./gradlew assembleDebug
```

5. Install on device:
```bash
./gradlew installDebug
```

Or use the "Run" button in Android Studio.

### Setting as Default Launcher

1. After installation, press the Home button
2. Android will show a launcher picker dialog
3. Select "LCARS Launcher"
4. Choose "Always" to set as default

Alternatively:
1. Open Settings → Apps → Default apps → Home app
2. Select "LCARS Launcher"

## Usage

### Home Screen

The home screen consists of three main areas:

**Left Rail (Status)**
- Current time (24-hour format)
- Current date
- System status indicators

**Center Deck**
- Deck header showing current deck number and profile
- APPS button to open the app drawer
- FAVORITES section showing pinned apps (first 8 apps by default)
- Profile switcher buttons at bottom

**Right Rail (Quick Actions)**
- Settings
- WiFi
- Volume
- Power/Battery

### App Drawer

Open the app drawer by tapping the large "APPS" button on the home screen.

Features:
- Search bar at top for filtering apps
- List of all installed applications
- App count display
- Category tags for each app
- Tap any app to launch it
- Close button to return to home

### Switching Profiles

Tap any of the profile buttons at the bottom of the home screen:
- **BRIDGE** - Classic LCARS colors
- **ENG** - Engineering yellow/orange theme
- **TAC** - Tactical blue/cyan theme
- **ALERT** - Red alert mode

## Customization

### Adding Custom Sounds

To add LCARS-style UI sounds:

1. Create the directory: `app/src/main/assets/lcars_sounds/`
2. Add your sound files (must be legally owned or from free/open sources)
3. Sounds should be short (< 1 second) for button taps
4. Use MP3 or OGG format

**Note:** Sound playback is prepared but not yet implemented in this version.

### Modifying Colors

Edit `app/src/main/java/com/lcars/launcher/ui/theme/LcarsPalette.kt`:

```kotlin
val MyCustomPalette = LcarsPalette(
    background = Color(0xFF000000),
    panelPrimary = Color(0xFFFFCC99),
    // ... other colors
)
```

### Adding New Profiles

1. Add a new palette in `LcarsPalettes.kt`
2. Add an enum value to `LcarsPaletteType`
3. Add a button in the deck view

## Technical Details

### Dependencies

- **Jetpack Compose** - Modern declarative UI
- **Room** - Local database persistence
- **DataStore** - Preferences storage
- **Hilt** - Dependency injection
- **Kotlin Coroutines** - Asynchronous operations
- **Flow** - Reactive data streams

### Architecture

The app follows clean architecture principles:

- **Data Layer**: Room database, repositories, data sources
- **Domain Layer**: Use cases and business logic (embedded in ViewModels for simplicity)
- **UI Layer**: Compose UI, ViewModels, theme system

### Performance

- Lazy loading for app lists
- Flow-based reactive updates
- Efficient Compose recomposition
- Room database with indexing
- Background thread for heavy operations

## Future Enhancements

### Planned Features
- [ ] Swipe gestures for deck navigation
- [ ] Widget support and integration
- [ ] Custom panel configurations per deck
- [ ] Sound and haptic feedback
- [ ] Voice command system ("Computer, open...")
- [ ] Multiple deck support with swipe navigation
- [ ] App favorites management
- [ ] Custom shortcuts and missions
- [ ] Immersive mode toggles
- [ ] Performance mode for low-end devices
- [ ] Backup and restore configurations

### Possible Extensions
- Stardate calculations
- System resource monitoring
- Weather widget integration
- Calendar/agenda panels
- Custom fonts support
- Animation speed controls
- Accessibility improvements

## Troubleshooting

### Launcher doesn't appear as option
- Ensure the app is installed
- Check that HOME intent filters are in AndroidManifest.xml
- Restart the device

### Apps don't launch
- Verify `QUERY_ALL_PACKAGES` permission in manifest
- Check that package names are correct
- Some system apps may not be launchable

### UI performance issues
- Enable performance mode (when implemented)
- Reduce animation scale in developer options
- Close background apps

## License

This project is for **PERSONAL USE ONLY**.

The code is provided as-is without warranty. The LCARS visual design is inspired by the aesthetic seen in Star Trek, but this implementation uses generic geometric designs and does not include any copyrighted assets.

## Disclaimer

This launcher is a fan project and is not affiliated with, endorsed by, or connected to CBS, Paramount, or any Star Trek rights holders. LCARS and Star Trek are trademarks of CBS Studios Inc.

## Contributing

This is a personal project, but you're welcome to fork it for your own use. If you create improvements or fixes, feel free to share them.

## Acknowledgments

- Inspired by the LCARS interface design aesthetic
- Built with modern Android development tools
- Uses open-source libraries and frameworks

---

**Version:** 1.0.0
**Min SDK:** 29 (Android 10)
**Target SDK:** 34 (Android 14)
**Language:** Kotlin
**UI Framework:** Jetpack Compose