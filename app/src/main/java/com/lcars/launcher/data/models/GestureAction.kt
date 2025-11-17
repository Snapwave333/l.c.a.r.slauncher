package com.lcars.launcher.data.models

/**
 * Data models for gesture mappings
 */
data class GestureMapping(
    val id: String,
    val gestureType: GestureType,
    val actionType: ActionType,
    val actionPayload: String?,
    val enabled: Boolean = true
)

enum class GestureType {
    SWIPE_UP,
    SWIPE_DOWN,
    SWIPE_LEFT,
    SWIPE_RIGHT,
    DOUBLE_TAP,
    LONG_PRESS
}

enum class ActionType {
    OPEN_APP_DRAWER,
    SWITCH_DECK_NEXT,
    SWITCH_DECK_PREV,
    SWITCH_PROFILE,
    OPEN_SETTINGS,
    OPEN_APP,
    RUN_SHORTCUT,
    CUSTOM_INTENT,
    TOGGLE_FLASHLIGHT,
    TOGGLE_WIFI,
    NONE
}
