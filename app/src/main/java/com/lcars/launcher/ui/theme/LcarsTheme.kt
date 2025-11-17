package com.lcars.launcher.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocal for LCARS Palette
 */
val LocalLcarsPalette = staticCompositionLocalOf { LcarsPalettes.Bridge }

/**
 * LCARS Theme
 * Provides the LCARS visual system to all composables
 */
@Composable
fun LcarsTheme(
    palette: LcarsPalette = LcarsPalettes.Bridge,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalLcarsPalette provides palette,
        content = content
    )
}

/**
 * Access the current LCARS palette
 */
object LcarsTheme {
    val palette: LcarsPalette
        @Composable
        get() = LocalLcarsPalette.current
}
