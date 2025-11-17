package com.lcars.launcher.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lcars.launcher.services.SystemStats
import com.lcars.launcher.ui.theme.LcarsTheme

/**
 * System metrics display panel
 */
@Composable
fun LcarsSystemMetrics(
    systemStats: SystemStats,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Battery
        MetricRow(
            label = "BATTERY",
            value = "${systemStats.batteryLevel}%${if (systemStats.isCharging) " CHG" else ""}",
            progress = systemStats.batteryLevel / 100f,
            color = when {
                systemStats.batteryLevel > 50 -> LcarsTheme.palette.statusGreen
                systemStats.batteryLevel > 20 -> LcarsTheme.palette.accentYellow
                else -> LcarsTheme.palette.statusRed
            }
        )

        // Network
        MetricRow(
            label = "NETWORK",
            value = systemStats.networkType,
            progress = if (systemStats.isNetworkConnected) 1f else 0f,
            color = if (systemStats.isNetworkConnected) LcarsTheme.palette.statusGreen else LcarsTheme.palette.statusRed
        )

        // CPU
        MetricRow(
            label = "CPU",
            value = "${systemStats.cpuUsage.toInt()}%",
            progress = systemStats.cpuUsage / 100f,
            color = when {
                systemStats.cpuUsage < 50 -> LcarsTheme.palette.statusGreen
                systemStats.cpuUsage < 80 -> LcarsTheme.palette.accentYellow
                else -> LcarsTheme.palette.statusRed
            }
        )

        // RAM
        MetricRow(
            label = "MEMORY",
            value = "${systemStats.totalRam - systemStats.availableRam}MB / ${systemStats.totalRam}MB",
            progress = systemStats.ramUsage / 100f,
            color = when {
                systemStats.ramUsage < 60 -> LcarsTheme.palette.statusGreen
                systemStats.ramUsage < 85 -> LcarsTheme.palette.accentYellow
                else -> LcarsTheme.palette.statusRed
            }
        )

        // Storage
        MetricRow(
            label = "STORAGE",
            value = String.format("%.1fGB / %.1fGB",
                systemStats.totalStorage - systemStats.availableStorage,
                systemStats.totalStorage),
            progress = systemStats.storageUsage / 100f,
            color = when {
                systemStats.storageUsage < 70 -> LcarsTheme.palette.statusGreen
                systemStats.storageUsage < 90 -> LcarsTheme.palette.accentYellow
                else -> LcarsTheme.palette.statusRed
            }
        )
    }
}

@Composable
private fun MetricRow(
    label: String,
    value: String,
    progress: Float,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = LcarsTheme.typography.label,
                color = LcarsTheme.palette.textPrimary
            )
            Text(
                text = value,
                style = LcarsTheme.typography.label,
                color = color
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        LinearProgressIndicator(
            progress = progress.coerceIn(0f, 1f),
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = color,
            trackColor = LcarsTheme.palette.backgroundSecondary
        )
    }
}
