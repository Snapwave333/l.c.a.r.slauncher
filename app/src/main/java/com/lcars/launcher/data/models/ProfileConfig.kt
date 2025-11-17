package com.lcars.launcher.data.models

import com.lcars.launcher.ui.theme.LcarsPaletteType

/**
 * Data model for profile/mode configurations
 */
data class ProfileConfig(
    val id: String,
    val name: String,
    val paletteType: LcarsPaletteType,
    val primaryDeckId: String?,
    val triggers: List<ProfileTrigger> = emptyList(),
    val soundEnabled: Boolean = true,
    val hapticsEnabled: Boolean = true
)

data class ProfileTrigger(
    val type: TriggerType,
    val value: String
)

enum class TriggerType {
    BATTERY_LOW,      // value: percentage threshold
    TIME_RANGE,       // value: "HH:mm-HH:mm"
    DND_MODE,         // value: "true"/"false"
    CHARGING,         // value: "true"/"false"
    MANUAL            // value: null
}
