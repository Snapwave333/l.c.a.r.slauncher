package com.lcars.launcher.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for storing voice command mappings
 */
@Entity(tableName = "voice_commands")
data class VoiceCommandEntity(
    @PrimaryKey
    val id: String,
    val phrase: String,  // Voice command phrase (e.g., "computer open communications")
    val actionType: String,  // OPEN_APP, SWITCH_PROFILE, RUN_SHORTCUT, CUSTOM_INTENT
    val actionPayload: String,
    val enabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
