package com.lcars.launcher.data.models

/**
 * Data models for deck and panel configurations
 */
data class DeckConfig(
    val id: String,
    val name: String,
    val displayOrder: Int,
    val panels: List<PanelConfig> = emptyList()
)

data class PanelConfig(
    val id: String,
    val deckId: String,
    val position: Int,
    val type: PanelType,
    val label: String,
    val payload: String,
    val colorOverride: String? = null,
    val width: Int = 1,
    val height: Int = 1
)

enum class PanelType {
    APP,
    SHORTCUT,
    WIDGET_SLOT,
    PROFILE_TOGGLE,
    CUSTOM_INTENT,
    QUICK_ACTION
}
