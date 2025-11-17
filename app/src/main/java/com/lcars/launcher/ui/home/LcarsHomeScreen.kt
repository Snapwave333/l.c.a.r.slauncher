package com.lcars.launcher.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lcars.launcher.ui.home.components.LcarsDeckView
import com.lcars.launcher.ui.home.components.LcarsStatusRailLeft
import com.lcars.launcher.ui.home.components.LcarsQuickActionsRailRight
import com.lcars.launcher.ui.drawer.AppDrawer
import com.lcars.launcher.ui.theme.LcarsTheme

/**
 * Main LCARS home screen layout
 */
@Composable
fun LcarsHomeScreen(
    viewModel: LcarsHomeViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val installedApps by viewModel.installedApps.collectAsState()
    val currentDeckIndex by viewModel.currentDeckIndex.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LcarsTheme.palette.background)
    ) {
        // Main home screen with rails and deck
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Left status rail
            LcarsStatusRailLeft(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
            )

            // Center deck area
            LcarsDeckView(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp),
                viewModel = viewModel,
                deckIndex = currentDeckIndex,
                onAppDrawerOpen = { viewModel.onAppDrawerToggle() }
            )

            // Right quick actions rail
            LcarsQuickActionsRailRight(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                viewModel = viewModel
            )
        }

        // App drawer overlay
        if (uiState.isAppDrawerOpen) {
            AppDrawer(
                apps = installedApps,
                searchQuery = uiState.searchQuery,
                onSearchQueryChange = { viewModel.onSearchQueryChanged(it) },
                onAppClick = { app -> viewModel.launchApp(app.packageName) },
                onClose = { viewModel.onAppDrawerClose() },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
