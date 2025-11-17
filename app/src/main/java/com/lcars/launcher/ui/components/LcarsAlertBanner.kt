package com.lcars.launcher.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lcars.launcher.ui.theme.LcarsShapes
import com.lcars.launcher.ui.theme.LcarsTheme
import com.lcars.launcher.ui.theme.LcarsTypography

/**
 * LCARS Alert Banner
 * Displays important notifications with optional pulsing animation
 */
@Composable
fun LcarsAlertBanner(
    message: String,
    modifier: Modifier = Modifier,
    alertLevel: AlertLevel = AlertLevel.NORMAL,
    pulse: Boolean = false
) {
    val alpha = if (pulse) {
        val infiniteTransition = rememberInfiniteTransition(label = "alert_pulse")
        val alphaAnimation by infiniteTransition.animateFloat(
            initialValue = 0.6f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(800, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "alert_alpha"
        )
        alphaAnimation
    } else {
        1f
    }

    val color = when (alertLevel) {
        AlertLevel.NORMAL -> LcarsTheme.palette.statusDeepBlue
        AlertLevel.WARNING -> LcarsTheme.palette.accentOrange
        AlertLevel.CRITICAL -> LcarsTheme.palette.alertRed
        AlertLevel.RED_ALERT -> LcarsTheme.palette.alertRedPulse
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(LcarsShapes.Medium)
            .background(color)
            .alpha(alpha)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message.uppercase(),
            style = LcarsTypography.TitleMedium,
            color = LcarsTheme.palette.textPrimary,
            textAlign = TextAlign.Center
        )
    }
}

enum class AlertLevel {
    NORMAL,
    WARNING,
    CRITICAL,
    RED_ALERT
}
