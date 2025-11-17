package com.lcars.launcher.data.repository

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.lcars.launcher.data.models.AppInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing installed applications
 */
@Singleton
class AppsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val packageManager = context.packageManager

    /**
     * Get all launchable apps
     */
    fun getInstalledApps(): Flow<List<AppInfo>> = flow {
        val intent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val apps = packageManager.queryIntentActivities(intent, 0)
            .mapNotNull { resolveInfo ->
                try {
                    val packageName = resolveInfo.activityInfo.packageName
                    val appInfo = packageManager.getApplicationInfo(packageName, 0)
                    val appName = packageManager.getApplicationLabel(appInfo).toString()
                    val icon = packageManager.getApplicationIcon(packageName)
                    val isSystemApp = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0

                    AppInfo(
                        packageName = packageName,
                        appName = appName,
                        icon = icon,
                        category = getCategoryName(appInfo.category),
                        isSystemApp = isSystemApp
                    )
                } catch (e: Exception) {
                    null
                }
            }
            .sortedBy { it.appName.lowercase() }

        emit(apps)
    }.flowOn(Dispatchers.IO)

    /**
     * Launch an app by package name
     */
    fun launchApp(packageName: String): Boolean {
        return try {
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            intent?.let {
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(it)
                true
            } ?: false
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Search apps by name
     */
    suspend fun searchApps(query: String, apps: List<AppInfo>): List<AppInfo> {
        if (query.isBlank()) return apps

        val lowerQuery = query.lowercase()
        return apps.filter { app ->
            app.appName.lowercase().contains(lowerQuery) ||
                    app.packageName.lowercase().contains(lowerQuery)
        }
    }

    private fun getCategoryName(category: Int): String {
        return when (category) {
            ApplicationInfo.CATEGORY_GAME -> "Games"
            ApplicationInfo.CATEGORY_AUDIO -> "Audio"
            ApplicationInfo.CATEGORY_VIDEO -> "Video"
            ApplicationInfo.CATEGORY_IMAGE -> "Image"
            ApplicationInfo.CATEGORY_SOCIAL -> "Social"
            ApplicationInfo.CATEGORY_NEWS -> "News"
            ApplicationInfo.CATEGORY_MAPS -> "Maps"
            ApplicationInfo.CATEGORY_PRODUCTIVITY -> "Productivity"
            else -> "Other"
        }
    }
}
