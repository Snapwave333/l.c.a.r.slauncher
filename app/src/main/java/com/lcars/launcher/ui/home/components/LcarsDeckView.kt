package com.lcars.launcher.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lcars.launcher.ui.components.LcarsActionPanel
import com.lcars.launcher.ui.components.LcarsAppIcon
import com.lcars.launcher.ui.components.LcarsPanel
import com.lcars.launcher.ui.home.LcarsHomeViewModel
import com.lcars.launcher.ui.theme.LcarsTheme
import com.lcars.launcher.ui.theme.LcarsPaletteType

/**
 * Main deck view showing app shortcuts, widgets, and actions
 */
@Composable
fun LcarsDeckView(
    viewModel: LcarsHomeViewModel,
    deckIndex: Int,
    onAppDrawerOpen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val favorites = viewModel.getFavoritesApps()
    val currentProfile by viewModel.currentProfile.collectAsState()

    Column(
        modifier = modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Deck header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LcarsPanel(
                label = "DECK ${deckIndex + 1}",
                color = LcarsTheme.palette.statusDeepBlue,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            LcarsPanel(
                label = currentProfile.name.replace("_", " "),
                color = LcarsTheme.palette.accentMagenta,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            )
        }

        // Main app drawer button
        LcarsActionPanel(
            label = "APPS",
            onClick = onAppDrawerOpen,
            color = LcarsTheme.palette.accentOrange,
            modifier = Modifier.fillMaxWidth()
        )

        // Favorites section
        LcarsPanel(
            label = "FAVORITES",
            color = LcarsTheme.palette.panelSecondary,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )

        // Favorites grid
        if (favorites.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {
                items(favorites) { app ->
                    LcarsAppIcon(
                        appName = app.appName,
                        appIcon = app.icon,
                        onClick = { viewModel.launchApp(app.packageName) },
                        showLabel = true
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                LcarsPanel(
                    label = "NO FAVORITES SET",
                    color = LcarsTheme.palette.backgroundSecondary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Profile selector strip
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProfileButton(
                label = "BRIDGE",
                profile = LcarsPaletteType.BRIDGE,
                currentProfile = currentProfile,
                onClick = { viewModel.switchProfile(LcarsPaletteType.BRIDGE) },
                modifier = Modifier.weight(1f)
            )

            ProfileButton(
                label = "ENG",
                profile = LcarsPaletteType.ENGINEERING,
                currentProfile = currentProfile,
                onClick = { viewModel.switchProfile(LcarsPaletteType.ENGINEERING) },
                modifier = Modifier.weight(1f)
            )

            ProfileButton(
                label = "TAC",
                profile = LcarsPaletteType.TACTICAL,
                currentProfile = currentProfile,
                onClick = { viewModel.switchProfile(LcarsPaletteType.TACTICAL) },
                modifier = Modifier.weight(1f)
            )

            ProfileButton(
                label = "ALERT",
                profile = LcarsPaletteType.RED_ALERT,
                currentProfile = currentProfile,
                onClick = { viewModel.switchProfile(LcarsPaletteType.RED_ALERT) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ProfileButton(
    label: String,
    profile: LcarsPaletteType,
    currentProfile: LcarsPaletteType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = if (profile == currentProfile) {
        profile.getPalette().panelPrimary
    } else {
        LcarsTheme.palette.backgroundSecondary
    }

    LcarsPanel(
        label = label,
        color = color,
        onClick = onClick,
        modifier = modifier.height(56.dp)
    )
}
