package com.lcars.launcher.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * LCARS Color Palette System
 * Defines the core LCARS colors used throughout the UI
 */
data class LcarsPalette(
    val background: Color,
    val backgroundSecondary: Color,
    val panelPrimary: Color,
    val panelSecondary: Color,
    val accentOrange: Color,
    val accentMagenta: Color,
    val accentCyan: Color,
    val accentBlue: Color,
    val accentYellow: Color,
    val alertRed: Color,
    val alertRedPulse: Color,
    val statusPurple: Color,
    val statusDeepBlue: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textOnPanel: Color
)

// Base LCARS Colors
object LcarsColors {
    val Black = Color(0xFF000000)
    val DarkBlue = Color(0xFF0B0D2B)
    val DarkNavy = Color(0xFF1A1A2E)

    // LCARS Standard Palette
    val Buff = Color(0xFFFFCC99)
    val BuffDark = Color(0xFFCC9966)
    val Orange = Color(0xFFFF9966)
    val OrangeBright = Color(0xFFFFAA66)
    val Magenta = Color(0xFFCC6699)
    val MagentaBright = Color(0xFFDD77AA)
    val Blue = Color(0xFF9999FF)
    val BlueBright = Color(0xFFAAAAFF)
    val Cyan = Color(0xFF66CCFF)
    val CyanBright = Color(0xFF77DDFF)
    val Yellow = Color(0xFFFFFF99)
    val YellowBright = Color(0xFFFFFFAA)

    // Alert Colors
    val Red = Color(0xFFCC6666)
    val RedAlert = Color(0xFFFF3333)
    val RedAlertPulse = Color(0xFFFF6666)

    // Status Colors
    val Purple = Color(0xFF9966CC)
    val DeepBlue = Color(0xFF336699)

    // Text Colors
    val TextWhite = Color(0xFFFFFFFF)
    val TextGray = Color(0xFFCCCCCC)
    val TextDark = Color(0xFF333333)
}

/**
 * Predefined LCARS palettes for different modes
 */
object LcarsPalettes {
    val Bridge = LcarsPalette(
        background = LcarsColors.Black,
        backgroundSecondary = LcarsColors.DarkBlue,
        panelPrimary = LcarsColors.Buff,
        panelSecondary = LcarsColors.BuffDark,
        accentOrange = LcarsColors.Orange,
        accentMagenta = LcarsColors.Magenta,
        accentCyan = LcarsColors.Cyan,
        accentBlue = LcarsColors.Blue,
        accentYellow = LcarsColors.Yellow,
        alertRed = LcarsColors.Red,
        alertRedPulse = LcarsColors.RedAlertPulse,
        statusPurple = LcarsColors.Purple,
        statusDeepBlue = LcarsColors.DeepBlue,
        textPrimary = LcarsColors.TextWhite,
        textSecondary = LcarsColors.TextGray,
        textOnPanel = LcarsColors.TextDark
    )

    val Engineering = LcarsPalette(
        background = LcarsColors.Black,
        backgroundSecondary = LcarsColors.DarkBlue,
        panelPrimary = LcarsColors.Yellow,
        panelSecondary = LcarsColors.Orange,
        accentOrange = LcarsColors.OrangeBright,
        accentMagenta = LcarsColors.Red,
        accentCyan = LcarsColors.Cyan,
        accentBlue = LcarsColors.Blue,
        accentYellow = LcarsColors.YellowBright,
        alertRed = LcarsColors.Red,
        alertRedPulse = LcarsColors.RedAlertPulse,
        statusPurple = LcarsColors.Purple,
        statusDeepBlue = LcarsColors.DeepBlue,
        textPrimary = LcarsColors.TextWhite,
        textSecondary = LcarsColors.TextGray,
        textOnPanel = LcarsColors.TextDark
    )

    val Tactical = LcarsPalette(
        background = LcarsColors.Black,
        backgroundSecondary = LcarsColors.DarkBlue,
        panelPrimary = LcarsColors.Blue,
        panelSecondary = LcarsColors.Cyan,
        accentOrange = LcarsColors.Orange,
        accentMagenta = LcarsColors.MagentaBright,
        accentCyan = LcarsColors.CyanBright,
        accentBlue = LcarsColors.BlueBright,
        accentYellow = LcarsColors.Yellow,
        alertRed = LcarsColors.Red,
        alertRedPulse = LcarsColors.RedAlertPulse,
        statusPurple = LcarsColors.Purple,
        statusDeepBlue = LcarsColors.DeepBlue,
        textPrimary = LcarsColors.TextWhite,
        textSecondary = LcarsColors.TextGray,
        textOnPanel = LcarsColors.TextDark
    )

    val RedAlert = LcarsPalette(
        background = LcarsColors.Black,
        backgroundSecondary = LcarsColors.DarkBlue,
        panelPrimary = LcarsColors.RedAlert,
        panelSecondary = LcarsColors.Red,
        accentOrange = LcarsColors.OrangeBright,
        accentMagenta = LcarsColors.Red,
        accentCyan = LcarsColors.RedAlertPulse,
        accentBlue = LcarsColors.Red,
        accentYellow = LcarsColors.Orange,
        alertRed = LcarsColors.RedAlert,
        alertRedPulse = LcarsColors.RedAlertPulse,
        statusPurple = LcarsColors.Red,
        statusDeepBlue = LcarsColors.Red,
        textPrimary = LcarsColors.TextWhite,
        textSecondary = LcarsColors.TextGray,
        textOnPanel = LcarsColors.TextWhite
    )

    val Night = LcarsPalette(
        background = LcarsColors.Black,
        backgroundSecondary = LcarsColors.DarkNavy,
        panelPrimary = LcarsColors.DeepBlue,
        panelSecondary = LcarsColors.Purple,
        accentOrange = LcarsColors.BuffDark,
        accentMagenta = LcarsColors.Magenta,
        accentCyan = LcarsColors.Blue,
        accentBlue = LcarsColors.BlueBright,
        accentYellow = LcarsColors.BuffDark,
        alertRed = LcarsColors.Red,
        alertRedPulse = LcarsColors.RedAlertPulse,
        statusPurple = LcarsColors.Purple,
        statusDeepBlue = LcarsColors.DeepBlue,
        textPrimary = LcarsColors.TextGray,
        textSecondary = Color(0xFF888888),
        textOnPanel = LcarsColors.TextWhite
    )
}

/**
 * Enum for palette selection
 */
enum class LcarsPaletteType {
    BRIDGE,
    ENGINEERING,
    TACTICAL,
    RED_ALERT,
    NIGHT;

    fun getPalette(): LcarsPalette = when (this) {
        BRIDGE -> LcarsPalettes.Bridge
        ENGINEERING -> LcarsPalettes.Engineering
        TACTICAL -> LcarsPalettes.Tactical
        RED_ALERT -> LcarsPalettes.RedAlert
        NIGHT -> LcarsPalettes.Night
    }

    companion object {
        fun fromString(name: String): LcarsPaletteType {
            return entries.find { it.name == name } ?: BRIDGE
        }
    }
}
