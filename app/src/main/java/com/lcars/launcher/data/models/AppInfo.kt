package com.lcars.launcher.data.models

import android.graphics.drawable.Drawable

/**
 * Data model for installed app information
 */
data class AppInfo(
    val packageName: String,
    val appName: String,
    val icon: Drawable,
    val category: String = "Other",
    val isSystemApp: Boolean = false
)
