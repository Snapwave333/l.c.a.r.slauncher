package com.lcars.launcher.ui.home.components

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.lcars.launcher.ui.components.LcarsRailItem
import com.lcars.launcher.ui.components.LcarsVerticalRail
import com.lcars.launcher.ui.components.RailPosition
import com.lcars.launcher.ui.home.LcarsHomeViewModel
import com.lcars.launcher.ui.theme.LcarsTheme

/**
 * Right rail showing quick actions
 */
@Composable
fun LcarsQuickActionsRailRight(
    viewModel: LcarsHomeViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LcarsVerticalRail(
        modifier = modifier,
        position = RailPosition.END,
        backgroundColor = LcarsTheme.palette.backgroundSecondary
    ) {
        LcarsRailItem(
            label = "SETTINGS",
            onClick = {
                // Open Android settings
                context.startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
            },
            color = LcarsTheme.palette.accentBlue,
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = LcarsTheme.palette.textOnPanel
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LcarsRailItem(
            label = "WIFI",
            onClick = {
                // Open WiFi settings
                context.startActivity(Intent(android.provider.Settings.ACTION_WIFI_SETTINGS))
            },
            color = LcarsTheme.palette.accentCyan,
            icon = {
                Icon(
                    imageVector = Icons.Default.Wifi,
                    contentDescription = "WiFi",
                    tint = LcarsTheme.palette.textOnPanel
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LcarsRailItem(
            label = "VOLUME",
            onClick = {
                // Open sound settings
                context.startActivity(Intent(android.provider.Settings.ACTION_SOUND_SETTINGS))
            },
            color = LcarsTheme.palette.accentYellow,
            icon = {
                Icon(
                    imageVector = Icons.Default.VolumeUp,
                    contentDescription = "Volume",
                    tint = LcarsTheme.palette.textOnPanel
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LcarsRailItem(
            label = "POWER",
            onClick = {
                // Open power/battery settings
                context.startActivity(Intent(Intent.ACTION_POWER_USAGE_SUMMARY))
            },
            color = LcarsTheme.palette.accentOrange,
            icon = {
                Icon(
                    imageVector = Icons.Default.BatteryFull,
                    contentDescription = "Power",
                    tint = LcarsTheme.palette.textOnPanel
                )
            }
        )
    }
}
