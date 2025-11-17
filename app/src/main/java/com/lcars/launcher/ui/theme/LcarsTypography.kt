package com.lcars.launcher.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

/**
 * LCARS Typography System
 * All text should be uppercase with generous letter spacing
 */
object LcarsTypography {
    // Large display text (deck titles, main headers)
    val DisplayLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        letterSpacing = 2.sp,
        lineHeight = 56.sp
    )

    val DisplayMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        letterSpacing = 1.5.sp,
        lineHeight = 44.sp
    )

    val DisplaySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        letterSpacing = 1.sp,
        lineHeight = 36.sp
    )

    // Panel labels and titles
    val TitleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        letterSpacing = 1.sp,
        lineHeight = 32.sp
    )

    val TitleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.8.sp,
        lineHeight = 28.sp
    )

    val TitleSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = 0.6.sp,
        lineHeight = 24.sp
    )

    // Body text (app names, descriptions)
    val BodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 26.sp
    )

    val BodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.4.sp,
        lineHeight = 24.sp
    )

    val BodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.3.sp,
        lineHeight = 20.sp
    )

    // Labels (small indicators, status text)
    val LabelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 20.sp
    )

    val LabelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp,
        lineHeight = 16.sp
    )

    val LabelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        letterSpacing = 0.3.sp,
        lineHeight = 14.sp
    )

    // Time/Date displays
    val TimeDisplay = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        letterSpacing = 2.sp,
        lineHeight = 40.sp
    )

    val DateDisplay = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 1.sp,
        lineHeight = 24.sp
    )

    // Status indicators
    val StatusText = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 1.sp,
        lineHeight = 16.sp
    )
}
