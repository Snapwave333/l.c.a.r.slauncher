package com.lcars.launcher.plugin

import android.content.Context
import android.content.Intent

/**
 * Plugin API for external LCARS modules
 *
 * External apps can implement this interface to create LCARS-compatible plugins
 */
interface LcarsPluginAPI {

    /**
     * Plugin metadata
     */
    fun getPluginInfo(): PluginInfo

    /**
     * Initialize plugin
     */
    fun initialize(context: Context): Boolean

    /**
     * Execute plugin action
     */
    fun executeAction(action: String, parameters: Map<String, Any>): PluginResult

    /**
     * Get plugin UI component intent (if any)
     */
    fun getUIIntent(): Intent?

    /**
     * Plugin lifecycle callback
     */
    fun onDestroy()
}

/**
 * Plugin metadata
 */
data class PluginInfo(
    val id: String,
    val name: String,
    val version: String,
    val author: String,
    val description: String,
    val permissions: List<String> = emptyList()
)

/**
 * Plugin action result
 */
sealed class PluginResult {
    data class Success(val message: String, val data: Map<String, Any> = emptyMap()) : PluginResult()
    data class Error(val error: String) : PluginResult()
    object NotSupported : PluginResult()
}

/**
 * Plugin manager for discovering and managing plugins
 */
class PluginManager(private val context: Context) {

    private val loadedPlugins = mutableMapOf<String, LcarsPluginAPI>()

    /**
     * Register a plugin
     */
    fun registerPlugin(plugin: LcarsPluginAPI): Boolean {
        val info = plugin.getPluginInfo()

        return if (plugin.initialize(context)) {
            loadedPlugins[info.id] = plugin
            true
        } else {
            false
        }
    }

    /**
     * Unregister a plugin
     */
    fun unregisterPlugin(pluginId: String) {
        loadedPlugins[pluginId]?.onDestroy()
        loadedPlugins.remove(pluginId)
    }

    /**
     * Get all loaded plugins
     */
    fun getLoadedPlugins(): List<PluginInfo> {
        return loadedPlugins.values.map { it.getPluginInfo() }
    }

    /**
     * Execute plugin action
     */
    fun executePluginAction(pluginId: String, action: String, parameters: Map<String, Any> = emptyMap()): PluginResult {
        val plugin = loadedPlugins[pluginId] ?: return PluginResult.Error("Plugin not found: $pluginId")
        return plugin.executeAction(action, parameters)
    }

    /**
     * Get plugin UI intent
     */
    fun getPluginUIIntent(pluginId: String): Intent? {
        return loadedPlugins[pluginId]?.getUIIntent()
    }

    /**
     * Cleanup all plugins
     */
    fun cleanup() {
        loadedPlugins.values.forEach { it.onDestroy() }
        loadedPlugins.clear()
    }
}

/**
 * Example plugin implementation
 */
class ExampleLcarsPlugin : LcarsPluginAPI {

    override fun getPluginInfo(): PluginInfo {
        return PluginInfo(
            id = "com.lcars.plugin.example",
            name = "Example Plugin",
            version = "1.0.0",
            author = "LCARS Team",
            description = "Example plugin showing the API usage",
            permissions = listOf()
        )
    }

    override fun initialize(context: Context): Boolean {
        // Initialize plugin resources
        return true
    }

    override fun executeAction(action: String, parameters: Map<String, Any>): PluginResult {
        return when (action) {
            "test" -> PluginResult.Success("Test action executed")
            else -> PluginResult.NotSupported
        }
    }

    override fun getUIIntent(): Intent? {
        return null
    }

    override fun onDestroy() {
        // Cleanup resources
    }
}
