package com.lcars.launcher.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.accessibilityDataStore: DataStore<Preferences> by preferencesDataStore(name = "accessibility_settings")

/**
 * Accessibility settings manager
 */
@Singleton
class AccessibilitySettings @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.accessibilityDataStore

    companion object {
        val LARGE_TEXT_MODE = booleanPreferencesKey("large_text_mode")
        val HIGH_CONTRAST_MODE = booleanPreferencesKey("high_contrast_mode")
        val COLOR_BLIND_MODE = stringPreferencesKey("color_blind_mode") // NONE, PROTANOPIA, DEUTERANOPIA, TRITANOPIA
        val REDUCE_ANIMATIONS = booleanPreferencesKey("reduce_animations")
        val LARGE_PANELS = booleanPreferencesKey("large_panels")
        val SCREEN_READER_MODE = booleanPreferencesKey("screen_reader_mode")
        val TOUCH_ASSIST_SIZE = floatPreferencesKey("touch_assist_size") // 1.0 = normal, 1.5 = large, 2.0 = extra large
    }

    // Large text mode
    val largeTextMode: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[LARGE_TEXT_MODE] ?: false
    }

    suspend fun setLargeTextMode(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[LARGE_TEXT_MODE] = enabled
        }
    }

    // High contrast mode
    val highContrastMode: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[HIGH_CONTRAST_MODE] ?: false
    }

    suspend fun setHighContrastMode(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[HIGH_CONTRAST_MODE] = enabled
        }
    }

    // Color blind mode
    val colorBlindMode: Flow<String> = dataStore.data.map { prefs ->
        prefs[COLOR_BLIND_MODE] ?: "NONE"
    }

    suspend fun setColorBlindMode(mode: String) {
        dataStore.edit { prefs ->
            prefs[COLOR_BLIND_MODE] = mode
        }
    }

    // Reduce animations
    val reduceAnimations: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[REDUCE_ANIMATIONS] ?: false
    }

    suspend fun setReduceAnimations(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[REDUCE_ANIMATIONS] = enabled
        }
    }

    // Large panels
    val largePanels: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[LARGE_PANELS] ?: false
    }

    suspend fun setLargePanels(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[LARGE_PANELS] = enabled
        }
    }

    // Touch assist size
    val touchAssistSize: Flow<Float> = dataStore.data.map { prefs ->
        prefs[TOUCH_ASSIST_SIZE] ?: 1.0f
    }

    suspend fun setTouchAssistSize(size: Float) {
        dataStore.edit { prefs ->
            prefs[TOUCH_ASSIST_SIZE] = size.coerceIn(1.0f, 2.0f)
        }
    }

    /**
     * Get recommended color palette adjustments for color blind mode
     */
    fun getColorBlindPaletteAdjustments(mode: String): Map<String, String> {
        return when (mode) {
            "PROTANOPIA" -> mapOf(
                "red" to "#0000FF",
                "green" to "#00FFFF"
            )
            "DEUTERANOPIA" -> mapOf(
                "red" to "#0066FF",
                "green" to "#FFCC00"
            )
            "TRITANOPIA" -> mapOf(
                "blue" to "#FF0066",
                "yellow" to "#00CCFF"
            )
            else -> emptyMap()
        }
    }
}
