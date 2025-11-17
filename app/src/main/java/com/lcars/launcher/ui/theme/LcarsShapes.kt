package com.lcars.launcher.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/**
 * LCARS Shape System
 * Defines the characteristic rounded rectangle shapes used in LCARS
 */
object LcarsShapes {
    // Large panels with significant rounding
    val Large = RoundedCornerShape(24.dp)
    val LargeAsymmetric = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 8.dp,
        bottomEnd = 24.dp,
        bottomStart = 8.dp
    )

    // Medium panels (most common)
    val Medium = RoundedCornerShape(16.dp)
    val MediumAsymmetric = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 4.dp,
        bottomEnd = 16.dp,
        bottomStart = 4.dp
    )

    // Small elements (buttons, indicators)
    val Small = RoundedCornerShape(12.dp)
    val SmallAsymmetric = RoundedCornerShape(
        topStart = 12.dp,
        topEnd = 2.dp,
        bottomEnd = 12.dp,
        bottomStart = 2.dp
    )

    // Rails and status bars (one side rounded)
    val RailStart = RoundedCornerShape(
        topStart = 32.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp,
        bottomStart = 32.dp
    )

    val RailEnd = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 32.dp,
        bottomEnd = 32.dp,
        bottomStart = 0.dp
    )

    val RailTop = RoundedCornerShape(
        topStart = 32.dp,
        topEnd = 32.dp,
        bottomEnd = 0.dp,
        bottomStart = 0.dp
    )

    val RailBottom = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomEnd = 32.dp,
        bottomStart = 32.dp
    )

    // Full rounded (for circular indicators)
    val Circle = RoundedCornerShape(50)
}
