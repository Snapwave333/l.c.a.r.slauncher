package com.lcars.launcher.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for storing individual panel configurations
 */
@Entity(tableName = "panel_configs")
data class PanelConfigEntity(
    @PrimaryKey
    val id: String,
    val deckId: String,
    val position: Int,  // Position in deck layout
    val panelType: String,  // APP, SHORTCUT, WIDGET_SLOT, PROFILE_TOGGLE, CUSTOM_INTENT
    val label: String,
    val payload: String,  // Package name, widget ID, intent data, etc.
    val colorOverride: String? = null,  // Optional hex color override
    val width: Int = 1,  // Grid width (1-4)
    val height: Int = 1  // Grid height (1-4)
)
