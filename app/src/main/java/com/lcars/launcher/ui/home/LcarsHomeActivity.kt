package com.lcars.launcher.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcars.launcher.ui.theme.LcarsTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main launcher activity for LCARS Launcher
 * This activity is registered as HOME and LAUNCHER in the manifest
 */
@AndroidEntryPoint
class LcarsHomeActivity : ComponentActivity() {

    private val viewModel: LcarsHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val currentProfile by viewModel.currentProfile.collectAsState()
            val palette = currentProfile.getPalette()

            LcarsTheme(palette = palette) {
                // System UI controller for status/nav bars
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = palette.background,
                    darkIcons = false
                )

                LcarsHomeScreen(
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    override fun onBackPressed() {
        // Prevent back button from exiting launcher
        // Instead, close app drawer if open
        if (viewModel.uiState.value.isAppDrawerOpen) {
            viewModel.onAppDrawerClose()
        }
        // Don't call super - this prevents exiting the launcher
    }
}
