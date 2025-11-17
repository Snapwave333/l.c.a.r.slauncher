package com.lcars.launcher.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "lcars_preferences")

/**
 * DataStore-based preferences manager for LCARS Launcher
 */
class LcarsPreferences(private val context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val CURRENT_PROFILE_ID = stringPreferencesKey("current_profile_id")
        val CURRENT_DECK_INDEX = intPreferencesKey("current_deck_index")
        val SOUND_ENABLED = booleanPreferencesKey("sound_enabled")
        val HAPTICS_ENABLED = booleanPreferencesKey("haptics_enabled")
        val IMMERSIVE_MODE = booleanPreferencesKey("immersive_mode")
        val HIDE_STATUS_BAR = booleanPreferencesKey("hide_status_bar")
        val PERFORMANCE_MODE = booleanPreferencesKey("performance_mode")
        val SHOW_APP_LABELS = booleanPreferencesKey("show_app_labels")
        val FIRST_RUN_COMPLETED = booleanPreferencesKey("first_run_completed")
        val VOICE_COMMANDS_ENABLED = booleanPreferencesKey("voice_commands_enabled")
        val GESTURE_NAVIGATION_ENABLED = booleanPreferencesKey("gesture_navigation_enabled")
    }

    // Current profile
    val currentProfileId: Flow<String> = dataStore.data.map { prefs ->
        prefs[CURRENT_PROFILE_ID] ?: "bridge"
    }

    suspend fun setCurrentProfileId(profileId: String) {
        dataStore.edit { prefs ->
            prefs[CURRENT_PROFILE_ID] = profileId
        }
    }

    // Current deck index
    val currentDeckIndex: Flow<Int> = dataStore.data.map { prefs ->
        prefs[CURRENT_DECK_INDEX] ?: 0
    }

    suspend fun setCurrentDeckIndex(index: Int) {
        dataStore.edit { prefs ->
            prefs[CURRENT_DECK_INDEX] = index
        }
    }

    // Sound enabled
    val soundEnabled: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[SOUND_ENABLED] ?: true
    }

    suspend fun setSoundEnabled(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[SOUND_ENABLED] = enabled
        }
    }

    // Haptics enabled
    val hapticsEnabled: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[HAPTICS_ENABLED] ?: true
    }

    suspend fun setHapticsEnabled(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[HAPTICS_ENABLED] = enabled
        }
    }

    // Immersive mode
    val immersiveMode: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[IMMERSIVE_MODE] ?: true
    }

    suspend fun setImmersiveMode(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[IMMERSIVE_MODE] = enabled
        }
    }

    // Hide status bar
    val hideStatusBar: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[HIDE_STATUS_BAR] ?: false
    }

    suspend fun setHideStatusBar(hide: Boolean) {
        dataStore.edit { prefs ->
            prefs[HIDE_STATUS_BAR] = hide
        }
    }

    // Performance mode
    val performanceMode: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[PERFORMANCE_MODE] ?: false
    }

    suspend fun setPerformanceMode(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[PERFORMANCE_MODE] = enabled
        }
    }

    // Show app labels
    val showAppLabels: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[SHOW_APP_LABELS] ?: true
    }

    suspend fun setShowAppLabels(show: Boolean) {
        dataStore.edit { prefs ->
            prefs[SHOW_APP_LABELS] = show
        }
    }

    // First run completed
    val firstRunCompleted: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[FIRST_RUN_COMPLETED] ?: false
    }

    suspend fun setFirstRunCompleted(completed: Boolean) {
        dataStore.edit { prefs ->
            prefs[FIRST_RUN_COMPLETED] = completed
        }
    }

    // Voice commands enabled
    val voiceCommandsEnabled: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[VOICE_COMMANDS_ENABLED] ?: false
    }

    suspend fun setVoiceCommandsEnabled(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[VOICE_COMMANDS_ENABLED] = enabled
        }
    }

    // Gesture navigation enabled
    val gestureNavigationEnabled: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[GESTURE_NAVIGATION_ENABLED] ?: true
    }

    suspend fun setGestureNavigationEnabled(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[GESTURE_NAVIGATION_ENABLED] = enabled
        }
    }
}
