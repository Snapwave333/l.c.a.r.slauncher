package com.lcars.launcher.services

import android.app.NotificationManager
import android.content.Context
import com.lcars.launcher.data.local.dao.ProfileDao
import com.lcars.launcher.data.local.entities.ProfileEntity
import com.lcars.launcher.data.preferences.LcarsPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Service for handling automatic profile switching based on triggers
 */
@Singleton
class ProfileTriggerService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileDao: ProfileDao,
    private val systemMonitor: SystemMonitorService,
    private val preferences: LcarsPreferences
) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    /**
     * Check all triggers and switch profile if needed
     */
    suspend fun checkTriggersAndSwitch(): String? {
        val profiles = profileDao.getAllProfiles().first()

        for (profile in profiles) {
            if (shouldTriggerProfile(profile)) {
                return profile.paletteType
            }
        }

        return null
    }

    /**
     * Check if a profile should be triggered
     */
    private fun shouldTriggerProfile(profile: ProfileEntity): Boolean {
        if (profile.triggersJson == null) return false

        val triggers = try {
            Json.decodeFromString<ProfileTriggers>(profile.triggersJson)
        } catch (e: Exception) {
            return false
        }

        // Check battery trigger
        if (triggers.batteryLevelBelow != null) {
            val batteryLevel = systemMonitor.getBatteryLevel()
            if (batteryLevel < triggers.batteryLevelBelow) {
                return true
            }
        }

        // Check time range trigger
        if (triggers.timeRange != null) {
            if (isInTimeRange(triggers.timeRange)) {
                return true
            }
        }

        // Check DND mode trigger
        if (triggers.doNotDisturbMode == true) {
            if (isDoNotDisturbActive()) {
                return true
            }
        }

        // Check charging state trigger
        if (triggers.chargingState != null) {
            val isCharging = systemMonitor.isCharging()
            if (isCharging == triggers.chargingState) {
                return true
            }
        }

        return false
    }

    /**
     * Check if current time is in specified range
     */
    private fun isInTimeRange(range: TimeRange): Boolean {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        val currentTimeInMinutes = currentHour * 60 + currentMinute

        val startTimeInMinutes = range.startHour * 60 + range.startMinute
        val endTimeInMinutes = range.endHour * 60 + range.endMinute

        return if (startTimeInMinutes < endTimeInMinutes) {
            currentTimeInMinutes in startTimeInMinutes..endTimeInMinutes
        } else {
            // Handle overnight range
            currentTimeInMinutes >= startTimeInMinutes || currentTimeInMinutes <= endTimeInMinutes
        }
    }

    /**
     * Check if Do Not Disturb mode is active
     */
    private fun isDoNotDisturbActive(): Boolean {
        return try {
            val filter = notificationManager.currentInterruptionFilter
            filter == NotificationManager.INTERRUPTION_FILTER_NONE ||
                    filter == NotificationManager.INTERRUPTION_FILTER_PRIORITY ||
                    filter == NotificationManager.INTERRUPTION_FILTER_ALARMS
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Create profile with triggers
     */
    fun createProfileTriggers(
        batteryLevelBelow: Int? = null,
        timeRange: TimeRange? = null,
        doNotDisturbMode: Boolean? = null,
        chargingState: Boolean? = null
    ): String {
        val triggers = ProfileTriggers(
            batteryLevelBelow = batteryLevelBelow,
            timeRange = timeRange,
            doNotDisturbMode = doNotDisturbMode,
            chargingState = chargingState
        )

        return Json.encodeToString(ProfileTriggers.serializer(), triggers)
    }
}

@Serializable
data class ProfileTriggers(
    val batteryLevelBelow: Int? = null,
    val timeRange: TimeRange? = null,
    val doNotDisturbMode: Boolean? = null,
    val chargingState: Boolean? = null
)

@Serializable
data class TimeRange(
    val startHour: Int,
    val startMinute: Int,
    val endHour: Int,
    val endMinute: Int
)
