package com.lcars.launcher.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.hiddenAppsDataStore: DataStore<Preferences> by preferencesDataStore(name = "hidden_apps")

/**
 * Manager for hidden apps feature
 */
@Singleton
class HiddenAppsManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.hiddenAppsDataStore

    companion object {
        private val HIDDEN_APPS_KEY = stringSetPreferencesKey("hidden_apps_set")
    }

    /**
     * Get hidden apps as Flow
     */
    fun getHiddenApps(): Flow<Set<String>> = dataStore.data.map { prefs ->
        prefs[HIDDEN_APPS_KEY] ?: emptySet()
    }

    /**
     * Add app to hidden list
     */
    suspend fun hideApp(packageName: String) {
        dataStore.edit { prefs ->
            val currentHidden = prefs[HIDDEN_APPS_KEY] ?: emptySet()
            prefs[HIDDEN_APPS_KEY] = currentHidden + packageName
        }
    }

    /**
     * Remove app from hidden list
     */
    suspend fun unhideApp(packageName: String) {
        dataStore.edit { prefs ->
            val currentHidden = prefs[HIDDEN_APPS_KEY] ?: emptySet()
            prefs[HIDDEN_APPS_KEY] = currentHidden - packageName
        }
    }

    /**
     * Check if app is hidden
     */
    suspend fun isAppHidden(packageName: String): Boolean {
        var isHidden = false
        dataStore.data.collect { prefs ->
            val hiddenApps = prefs[HIDDEN_APPS_KEY] ?: emptySet()
            isHidden = packageName in hiddenApps
        }
        return isHidden
    }

    /**
     * Toggle app visibility
     */
    suspend fun toggleAppVisibility(packageName: String) {
        if (isAppHidden(packageName)) {
            unhideApp(packageName)
        } else {
            hideApp(packageName)
        }
    }

    /**
     * Clear all hidden apps
     */
    suspend fun clearAllHidden() {
        dataStore.edit { prefs ->
            prefs[HIDDEN_APPS_KEY] = emptySet()
        }
    }
}
