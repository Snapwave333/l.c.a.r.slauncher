package com.lcars.launcher.ui.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lcars.launcher.ui.components.LcarsActionPanel
import com.lcars.launcher.ui.components.LcarsPanel
import com.lcars.launcher.ui.theme.LcarsTheme
import com.lcars.launcher.ui.theme.LcarsPalettes
import dagger.hilt.android.AndroidEntryPoint

/**
 * Settings activity for LCARS Launcher
 */
@AndroidEntryPoint
class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LcarsTheme(palette = LcarsPalettes.Bridge) {
                SettingsScreen(
                    onSetAsDefault = {
                        // Open default home app settings
                        val intent = Intent(Settings.ACTION_HOME_SETTINGS)
                        startActivity(intent)
                    },
                    onBack = { finish() }
                )
            }
        }
    }
}

@Composable
fun SettingsScreen(
    onSetAsDefault: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LcarsTheme.palette.background)
            .padding(16.dp)
    ) {
        // Header
        LcarsPanel(
            label = "LCARS LAUNCHER SETTINGS",
            color = LcarsTheme.palette.accentOrange,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            item {
                LcarsActionPanel(
                    label = "SET AS DEFAULT LAUNCHER",
                    onClick = onSetAsDefault,
                    color = LcarsTheme.palette.accentCyan
                )
            }

            item {
                LcarsPanel(
                    label = "VERSION 1.0.0",
                    color = LcarsTheme.palette.panelSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )
            }

            item {
                LcarsPanel(
                    label = "PERSONAL USE ONLY",
                    color = LcarsTheme.palette.statusDeepBlue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back button
        LcarsActionPanel(
            label = "BACK",
            onClick = onBack,
            color = LcarsTheme.palette.alertRed,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
