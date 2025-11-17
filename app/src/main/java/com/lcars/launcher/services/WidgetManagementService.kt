package com.lcars.launcher.services

import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Widget management service for LCARS widget slots
 */
@Singleton
class WidgetManagementService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(context)
    private val appWidgetHost: AppWidgetHost = AppWidgetHost(context, LCARS_WIDGET_HOST_ID)

    companion object {
        const val LCARS_WIDGET_HOST_ID = 1337
    }

    init {
        appWidgetHost.startListening()
    }

    /**
     * Get all available widgets
     */
    fun getAvailableWidgets(): List<AppWidgetProviderInfo> {
        return appWidgetManager.installedProviders
    }

    /**
     * Allocate a widget ID
     */
    fun allocateWidgetId(): Int {
        return appWidgetHost.allocateAppWidgetId()
    }

    /**
     * Bind widget to ID
     */
    fun bindWidget(widgetId: Int, providerInfo: AppWidgetProviderInfo): Boolean {
        return try {
            appWidgetManager.bindAppWidgetIdIfAllowed(widgetId, providerInfo.provider)
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Delete widget
     */
    fun deleteWidget(widgetId: Int) {
        appWidgetHost.deleteAppWidgetId(widgetId)
    }

    /**
     * Get widget info
     */
    fun getWidgetInfo(widgetId: Int): AppWidgetProviderInfo? {
        return appWidgetManager.getAppWidgetInfo(widgetId)
    }

    /**
     * Create widget host view
     */
    fun createWidgetView(widgetId: Int): android.appwidget.AppWidgetHostView? {
        val widgetInfo = getWidgetInfo(widgetId) ?: return null
        return appWidgetHost.createView(context, widgetId, widgetInfo)
    }

    /**
     * Stop listening (cleanup)
     */
    fun stopListening() {
        appWidgetHost.stopListening()
    }
}
