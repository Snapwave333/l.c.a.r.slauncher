# LCARS Launcher ProGuard Rules

# ===========================
# General Android Rules
# ===========================
-keepattributes *Annotation*, InnerClasses, Signature, SourceFile, LineNumberTable
-renamesourcefileattribute SourceFile

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# ===========================
# Jetpack Compose
# ===========================
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Keep all Composable functions
-keep @androidx.compose.runtime.Composable class * { *; }
-keep class * {
    @androidx.compose.runtime.Composable *;
}

# Keep ViewModels
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keep class * extends androidx.lifecycle.AndroidViewModel { *; }

# ===========================
# Room Database
# ===========================
-keep class com.lcars.launcher.data.local.entities.** { *; }
-keep class com.lcars.launcher.data.local.dao.** { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Entity class * { *; }
-dontwarn androidx.room.paging.**

# ===========================
# Hilt / Dagger
# ===========================
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.lifecycle.HiltViewModel { *; }
-keep class dagger.hilt.** { *; }
-keep class **_HiltModules { *; }
-keep class **_HiltModules$** { *; }
-keep @dagger.hilt.** class * { *; }
-keepclassmembers class * {
    @javax.inject.Inject <init>(...);
}

# ===========================
# Kotlin
# ===========================
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-keepclassmembers class **$WhenMappings { <fields>; }
-keepclassmembers class kotlin.Metadata { public <methods>; }
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

# Kotlinx Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** { volatile <fields>; }

# ===========================
# DataStore
# ===========================
-keep class androidx.datastore.** { *; }
-keepclassmembers class * extends androidx.datastore.core.Serializer { *; }

# ===========================
# Models & Data Classes
# ===========================
-keep class com.lcars.launcher.data.models.** { *; }
-keepclassmembers class com.lcars.launcher.data.models.** { *; }

# Keep all enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# ===========================
# Kotlinx Serialization
# ===========================
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.lcars.launcher.**$$serializer { *; }
-keepclassmembers class com.lcars.launcher.** {
    *** Companion;
}
-keepclasseswithmembers class com.lcars.launcher.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# ===========================
# Android Components
# ===========================
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# ===========================
# Reflection
# ===========================
-keepclassmembers class * {
    @androidx.compose.runtime.Composable *;
}

# ===========================
# Launcher Specific
# ===========================
# Keep launcher activity
-keep class com.lcars.launcher.ui.home.LcarsHomeActivity { *; }
-keep class com.lcars.launcher.LcarsLauncherApp { *; }

# Keep all theme and UI components
-keep class com.lcars.launcher.ui.theme.** { *; }
-keep class com.lcars.launcher.ui.components.** { *; }

# ===========================
# Warnings to Ignore
# ===========================
-dontwarn org.slf4j.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**
