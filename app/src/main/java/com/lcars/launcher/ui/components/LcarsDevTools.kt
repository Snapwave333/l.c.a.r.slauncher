package com.lcars.launcher.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lcars.launcher.BuildConfig
import com.lcars.launcher.ui.theme.LcarsTheme

/**
 * Developer tools panel
 */
@Composable
fun LcarsDevTools(
    onAdbWirelessToggle: () -> Unit,
    onShowLogs: () -> Unit,
    onClearData: () -> Unit,
    onTestHaptics: () -> Unit,
    onTestSounds: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Header
        LcarsPanel(
            label = "DEV TOOLS",
            color = LcarsTheme.palette.statusYellow,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )

        // App info
        InfoSection(
            title = "APP INFO",
            items = listOf(
                "Version" to BuildConfig.VERSION_NAME,
                "Build" to BuildConfig.VERSION_CODE.toString(),
                "Debug" to BuildConfig.DEBUG.toString()
            )
        )

        // Dev actions
        LcarsButton(
            label = "SHOW LOGS",
            onClick = onShowLogs,
            color = LcarsTheme.palette.accentCyan,
            modifier = Modifier.fillMaxWidth()
        )

        LcarsButton(
            label = "TEST HAPTICS",
            onClick = onTestHaptics,
            color = LcarsTheme.palette.accentMagenta,
            modifier = Modifier.fillMaxWidth()
        )

        LcarsButton(
            label = "TEST SOUNDS",
            onClick = onTestSounds,
            color = LcarsTheme.palette.accentMagenta,
            modifier = Modifier.fillMaxWidth()
        )

        LcarsButton(
            label = "CLEAR ALL DATA",
            onClick = onClearData,
            color = LcarsTheme.palette.statusRed,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun InfoSection(
    title: String,
    items: List<Pair<String, String>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = LcarsTheme.typography.label,
            color = LcarsTheme.palette.accentOrange
        )

        items.forEach { (key, value) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = key,
                    style = LcarsTheme.typography.body,
                    color = LcarsTheme.palette.textSecondary
                )
                Text(
                    text = value,
                    style = LcarsTheme.typography.body,
                    color = LcarsTheme.palette.textPrimary
                )
            }
        }
    }
}
