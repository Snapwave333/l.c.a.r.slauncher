package com.lcars.launcher.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for storing gesture to action mappings
 */
@Entity(tableName = "gesture_mappings")
data class GestureMappingEntity(
    @PrimaryKey
    val id: String,
    val gestureType: String,  // SWIPE_UP, SWIPE_DOWN, SWIPE_LEFT, SWIPE_RIGHT, DOUBLE_TAP, LONG_PRESS
    val actionType: String,   // OPEN_APP_DRAWER, SWITCH_DECK, SWITCH_PROFILE, CUSTOM_INTENT
    val actionPayload: String?,
    val enabled: Boolean = true
)
