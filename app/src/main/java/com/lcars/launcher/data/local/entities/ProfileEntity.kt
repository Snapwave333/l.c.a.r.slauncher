package com.lcars.launcher.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for storing profile/mode configurations
 */
@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val paletteType: String,  // BRIDGE, ENGINEERING, TACTICAL, RED_ALERT, NIGHT
    val primaryDeckId: String?,
    val triggersJson: String?,  // JSON for auto-trigger conditions (battery, time, etc.)
    val soundEnabled: Boolean = true,
    val hapticsEnabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
