# Building LCARS Launcher

Complete guide for building debug and release APKs of the LCARS Launcher.

## Prerequisites

### Required Software

- ✅ **Android Studio** Hedgehog (2023.1.1) or later
- ✅ **JDK 17** or later
- ✅ **Android SDK** with:
  - Platform SDK 34 (Android 14)
  - Build Tools 34.0.0 or later
  - Android SDK Platform-Tools
- ✅ **Gradle 8.2+** (included via wrapper)

### System Requirements

- **Windows**: 8GB RAM minimum, 16GB recommended
- **macOS**: 8GB RAM minimum, 16GB recommended
- **Linux**: 8GB RAM minimum, 16GB recommended
- **Storage**: At least 10GB free space

## Quick Build (Android Studio)

### Method 1: Using Android Studio GUI

1. **Open Project**
   ```
   File → Open → Select l.c.a.r.slauncher folder
   ```

2. **Wait for Gradle Sync**
   - Android Studio will automatically sync Gradle
   - Wait for dependencies to download (first time may take 5-10 minutes)

3. **Build Debug APK**
   ```
   Build → Build Bundle(s) / APK(s) → Build APK(s)
   ```

4. **Build Release APK**
   ```
   Build → Generate Signed Bundle / APK → APK → Create new...
   ```

### Method 2: Using Terminal in Android Studio

1. **Open Terminal** (bottom tab in Android Studio)

2. **Build Debug APK**
   ```bash
   ./gradlew assembleDebug
   ```

3. **Build Release APK**
   ```bash
   ./gradlew assembleRelease
   ```

## Command Line Build

### On macOS / Linux

1. **Navigate to project directory**
   ```bash
   cd /path/to/l.c.a.r.slauncher
   ```

2. **Make gradlew executable** (first time only)
   ```bash
   chmod +x gradlew
   ```

3. **Clean build** (optional but recommended)
   ```bash
   ./gradlew clean
   ```

4. **Build Debug APK**
   ```bash
   ./gradlew assembleDebug
   ```

5. **Build Release APK**
   ```bash
   ./gradlew assembleRelease
   ```

### On Windows

1. **Open Command Prompt or PowerShell**
   ```cmd
   cd C:\path\to\l.c.a.r.slauncher
   ```

2. **Build Debug APK**
   ```cmd
   gradlew.bat assembleDebug
   ```

3. **Build Release APK**
   ```cmd
   gradlew.bat assembleRelease
   ```

## Build Output Locations

After successful build, APKs will be located at:

### Debug APK
```
app/build/outputs/apk/debug/app-debug.apk
```

**Properties:**
- Package ID: `com.lcars.launcher.debug`
- Signed with: Debug keystore (auto-generated)
- Minified: No
- Size: ~15-20 MB

### Release APK
```
app/build/outputs/apk/release/app-release.apk
```

**Properties:**
- Package ID: `com.lcars.launcher`
- Signed with: Debug keystore (can be re-signed)
- Minified: Yes (ProGuard/R8 enabled)
- Shrunk: Yes (resource shrinking enabled)
- Size: ~8-12 MB (optimized)

## Build Variants

### Debug Build
- **Purpose**: Development and testing
- **Features**:
  - Includes debug symbols
  - No code obfuscation
  - Faster build times
  - Package suffix: `.debug`
  - Version suffix: `-debug`

### Release Build
- **Purpose**: Production deployment
- **Features**:
  - Code minification enabled
  - Resource shrinking enabled
  - ProGuard/R8 optimization
  - Smaller APK size
  - Better performance

## Advanced Build Options

### Building Specific Flavor

```bash
# Debug
./gradlew assembleDebug

# Release
./gradlew assembleRelease
```

### Clean Build (removes all build artifacts)

```bash
./gradlew clean
```

### Complete Clean + Rebuild

```bash
./gradlew clean assembleRelease
```

### Build with Verbose Output

```bash
./gradlew assembleRelease --info
```

### Build with Stack Trace (for debugging build errors)

```bash
./gradlew assembleRelease --stacktrace
```

### Offline Build (uses cached dependencies)

```bash
./gradlew assembleRelease --offline
```

## Signing the Release APK

The current configuration uses debug signing for the release APK. For production:

### Option 1: Sign with Android Studio

1. **Build → Generate Signed Bundle / APK**
2. Select **APK**
3. **Create new keystore** or use existing
4. Fill in keystore details:
   - Key store path
   - Password
   - Key alias
   - Key password
   - Validity (years): 25+

### Option 2: Sign via Command Line

1. **Create Keystore** (first time only)
   ```bash
   keytool -genkey -v -keystore lcars-release.keystore \
     -alias lcars-launcher -keyalg RSA -keysize 2048 -validity 10000
   ```

2. **Create `keystore.properties`** in project root
   ```properties
   storeFile=../lcars-release.keystore
   storePassword=your_store_password
   keyAlias=lcars-launcher
   keyPassword=your_key_password
   ```

3. **Update `app/build.gradle.kts`** (add before `buildTypes`)
   ```kotlin
   signingConfigs {
       create("release") {
           val keystorePropertiesFile = rootProject.file("keystore.properties")
           if (keystorePropertiesFile.exists()) {
               val keystoreProperties = Properties()
               keystoreProperties.load(FileInputStream(keystorePropertiesFile))

               storeFile = file(keystoreProperties["storeFile"] as String)
               storePassword = keystoreProperties["storePassword"] as String
               keyAlias = keystoreProperties["keyAlias"] as String
               keyPassword = keystoreProperties["keyPassword"] as String
           }
       }
   }
   ```

4. **Update release buildType**
   ```kotlin
   buildTypes {
       release {
           signingConfig = signingConfigs.getByName("release")
           // ... rest of config
       }
   }
   ```

5. **Build signed APK**
   ```bash
   ./gradlew assembleRelease
   ```

### Option 3: Manual APK Signing

After building unsigned APK:

```bash
# Align the APK
zipalign -v -p 4 app-release-unsigned.apk app-release-unsigned-aligned.apk

# Sign the APK
apksigner sign --ks lcars-release.keystore \
  --out app-release-signed.apk app-release-unsigned-aligned.apk

# Verify signature
apksigner verify app-release-signed.apk
```

## Installation

### Install Debug APK

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Install Release APK

```bash
adb install app/build/outputs/apk/release/app-release.apk
```

### Install with Overwrite

```bash
adb install -r app/build/outputs/apk/release/app-release.apk
```

### Install to Specific Device

```bash
# List devices
adb devices

# Install to specific device
adb -s DEVICE_SERIAL install app-release.apk
```

## Troubleshooting

### Build Fails: "SDK location not found"

**Solution:** Create `local.properties` in project root:

```properties
sdk.dir=/path/to/Android/Sdk
```

On macOS:
```properties
sdk.dir=/Users/YourUsername/Library/Android/sdk
```

On Windows:
```properties
sdk.dir=C\:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk
```

On Linux:
```properties
sdk.dir=/home/YourUsername/Android/Sdk
```

### Build Fails: "Gradle sync failed"

1. **File → Invalidate Caches / Restart**
2. Delete `.gradle` folder in project root
3. **Build → Clean Project**
4. **Build → Rebuild Project**

### Build Fails: "Compilation error"

1. Check Java version: `java -version` (should be 17+)
2. Update Android Studio to latest version
3. Sync Gradle files: **File → Sync Project with Gradle Files**

### Build Fails: OutOfMemoryError

Add to `gradle.properties`:

```properties
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=512m
```

### APK Install Fails: INSTALL_FAILED_UPDATE_INCOMPATIBLE

Uninstall existing version first:

```bash
adb uninstall com.lcars.launcher
adb install app-release.apk
```

### ProGuard Build Errors

If release build fails due to ProGuard:

1. Check `app/build/outputs/logs/proguard/release/missing_rules.txt`
2. Add missing rules to `app/proguard-rules.pro`
3. Or temporarily disable minification in `app/build.gradle.kts`:
   ```kotlin
   isMinifyEnabled = false
   ```

## Build Performance Tips

### Speed Up Builds

1. **Enable Gradle Daemon** (usually enabled by default)
   ```properties
   # gradle.properties
   org.gradle.daemon=true
   ```

2. **Enable Parallel Builds**
   ```properties
   org.gradle.parallel=true
   ```

3. **Enable Build Cache**
   ```properties
   org.gradle.caching=true
   ```

4. **Increase Gradle Memory**
   ```properties
   org.gradle.jvmargs=-Xmx4096m
   ```

5. **Use Configuration Cache** (Gradle 8.2+)
   ```bash
   ./gradlew assembleRelease --configuration-cache
   ```

### CI/CD Build

For automated builds (GitHub Actions, Jenkins, etc.):

```bash
# Non-interactive build
./gradlew assembleRelease --no-daemon --stacktrace

# With test reports
./gradlew clean assembleRelease test --no-daemon
```

## Verifying the Build

### Check APK Information

```bash
# Using aapt
aapt dump badging app-release.apk | grep -E 'package|version|sdk'

# Using aapt2
aapt2 dump packagename app-release.apk
```

### Check APK Size

```bash
ls -lh app/build/outputs/apk/release/app-release.apk
```

### Analyze APK

```bash
# In Android Studio
Build → Analyze APK → Select app-release.apk
```

This shows:
- APK size breakdown
- Methods count
- Resource usage
- Dex files

## Build Artifacts

After successful build, you'll find:

```
app/build/
├── outputs/
│   ├── apk/
│   │   ├── debug/
│   │   │   └── app-debug.apk
│   │   └── release/
│   │       └── app-release.apk
│   ├── mapping/
│   │   └── release/
│   │       ├── mapping.txt          # ProGuard mappings
│   │       ├── seeds.txt
│   │       └── usage.txt
│   └── logs/
│       └── proguard/
│           └── release/
│               └── missing_rules.txt
```

**Important Files:**
- **APKs**: Ready to install
- **mapping.txt**: Keep for crash deobfuscation (release builds)
- **missing_rules.txt**: ProGuard optimization info

## Next Steps

After building:

1. **Test on Device**
   - Install APK: `adb install app-release.apk`
   - Test all features
   - Check performance

2. **Set as Default Launcher**
   - Press HOME button
   - Select "LCARS Launcher"
   - Choose "Always"

3. **Report Issues**
   - Open GitHub issue with:
     - Device model
     - Android version
     - Build variant (debug/release)
     - Logcat output

---

**Note:** This launcher is for **PERSONAL USE ONLY**. Do not distribute release APKs publicly or upload to app stores.

For questions or issues, open a GitHub issue or discussion.
