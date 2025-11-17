package com.lcars.launcher.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lcars.launcher.services.SystemStats
import com.lcars.launcher.ui.components.LcarsRailInfo
import com.lcars.launcher.ui.components.LcarsVerticalRail
import com.lcars.launcher.ui.components.RailPosition
import com.lcars.launcher.ui.theme.LcarsTheme
import com.lcars.launcher.ui.theme.LcarsTypography
import com.lcars.launcher.utils.StardateCalculator
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

/**
 * Left status rail showing time, date, stardate, and device status
 */
@Composable
fun LcarsStatusRailLeft(
    systemStats: SystemStats = SystemStats(),
    modifier: Modifier = Modifier
) {
    var currentTime by remember { mutableStateOf(getCurrentTime()) }
    var currentDate by remember { mutableStateOf(getCurrentDate()) }
    var stardate by remember { mutableStateOf(StardateCalculator.getCurrentStardate()) }

    // Update time every second
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = getCurrentTime()
            currentDate = getCurrentDate()
            stardate = StardateCalculator.getCurrentStardate()
            delay(1000L)
        }
    }

    LcarsVerticalRail(
        modifier = modifier,
        position = RailPosition.START,
        backgroundColor = LcarsTheme.palette.backgroundSecondary
    ) {
        // Time display
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentTime,
                style = LcarsTypography.TimeDisplay,
                color = LcarsTheme.palette.textPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = currentDate,
                style = LcarsTypography.LabelMedium,
                color = LcarsTheme.palette.textSecondary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Stardate
        LcarsRailInfo(
            label = "STARDATE",
            value = stardate,
            color = LcarsTheme.palette.accentMagenta
        )

        Spacer(modifier = Modifier.height(16.dp))

        // System status info
        LcarsRailInfo(
            label = "BATTERY",
            value = "${systemStats.batteryLevel}%",
            color = when {
                systemStats.batteryLevel > 50 -> LcarsTheme.palette.statusGreen
                systemStats.batteryLevel > 20 -> LcarsTheme.palette.accentYellow
                else -> LcarsTheme.palette.statusRed
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LcarsRailInfo(
            label = "NETWORK",
            value = systemStats.networkType,
            color = if (systemStats.isNetworkConnected)
                LcarsTheme.palette.statusGreen
            else
                LcarsTheme.palette.statusRed
        )

        Spacer(modifier = Modifier.height(8.dp))

        LcarsRailInfo(
            label = "STATUS",
            value = "ONLINE",
            color = LcarsTheme.palette.accentCyan
        )
    }
}

private fun getCurrentTime(): String {
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatter.format(Date())
}

private fun getCurrentDate(): String {
    val formatter = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
    return formatter.format(Date()).uppercase()
}
