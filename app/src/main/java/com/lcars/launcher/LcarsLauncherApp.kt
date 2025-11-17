package com.lcars.launcher

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Main Application class for LCARS Launcher
 * Enables Hilt dependency injection
 */
@HiltAndroidApp
class LcarsLauncherApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
