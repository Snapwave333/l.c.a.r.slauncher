package com.lcars.launcher.services

import android.app.ActivityManager
import android.app.usage.StorageStatsManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.os.StatFs
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import java.io.RandomAccessFile
import javax.inject.Inject
import javax.inject.Singleton

/**
 * System monitoring service for battery, network, CPU, RAM, and storage
 */
@Singleton
class SystemMonitorService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val _systemStats = MutableStateFlow(SystemStats())
    val systemStats: StateFlow<SystemStats> = _systemStats.asStateFlow()

    private val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    /**
     * Update all system stats
     */
    fun updateSystemStats() {
        _systemStats.value = SystemStats(
            batteryLevel = getBatteryLevel(),
            isCharging = isCharging(),
            networkType = getNetworkType(),
            isNetworkConnected = isNetworkConnected(),
            cpuUsage = getCpuUsage(),
            ramUsage = getRamUsage(),
            totalRam = getTotalRam(),
            availableRam = getAvailableRam(),
            storageUsage = getStorageUsage(),
            totalStorage = getTotalStorage(),
            availableStorage = getAvailableStorage()
        )
    }

    /**
     * Get battery level (0-100)
     */
    fun getBatteryLevel(): Int {
        val batteryIntent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = batteryIntent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = batteryIntent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1

        return if (level != -1 && scale != -1) {
            ((level.toFloat() / scale.toFloat()) * 100).toInt()
        } else {
            0
        }
    }

    /**
     * Check if device is charging
     */
    fun isCharging(): Boolean {
        val batteryIntent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val status = batteryIntent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1

        return status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL
    }

    /**
     * Get network type
     */
    fun getNetworkType(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return "OFFLINE"
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return "OFFLINE"

            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WIFI"
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "CELLULAR"
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "ETHERNET"
                else -> "UNKNOWN"
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return "OFFLINE"

            @Suppress("DEPRECATION")
            return when (networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> "WIFI"
                ConnectivityManager.TYPE_MOBILE -> "CELLULAR"
                ConnectivityManager.TYPE_ETHERNET -> "ETHERNET"
                else -> "UNKNOWN"
            }
        }
    }

    /**
     * Check if network is connected
     */
    fun isNetworkConnected(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false

            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    /**
     * Get CPU usage percentage (approximate)
     */
    fun getCpuUsage(): Float {
        return try {
            val reader = RandomAccessFile("/proc/stat", "r")
            val load = reader.readLine()
            reader.close()

            val toks = load.split(" +".toRegex())
            val idle = toks[4].toLong()
            val cpu = toks.slice(1..7).sumOf { it.toLong() }

            val usage = ((cpu - idle).toFloat() / cpu.toFloat()) * 100
            usage.coerceIn(0f, 100f)
        } catch (e: Exception) {
            0f
        }
    }

    /**
     * Get RAM usage percentage
     */
    fun getRamUsage(): Float {
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        val totalMem = memoryInfo.totalMem.toFloat()
        val availMem = memoryInfo.availMem.toFloat()

        return ((totalMem - availMem) / totalMem) * 100
    }

    /**
     * Get total RAM in MB
     */
    fun getTotalRam(): Long {
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        return memoryInfo.totalMem / (1024 * 1024)
    }

    /**
     * Get available RAM in MB
     */
    fun getAvailableRam(): Long {
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        return memoryInfo.availMem / (1024 * 1024)
    }

    /**
     * Get storage usage percentage
     */
    fun getStorageUsage(): Float {
        val stat = StatFs(Environment.getDataDirectory().path)
        val totalBytes = stat.blockCountLong * stat.blockSizeLong
        val availableBytes = stat.availableBlocksLong * stat.blockSizeLong

        return ((totalBytes - availableBytes).toFloat() / totalBytes.toFloat()) * 100
    }

    /**
     * Get total storage in GB
     */
    fun getTotalStorage(): Float {
        val stat = StatFs(Environment.getDataDirectory().path)
        val totalBytes = stat.blockCountLong * stat.blockSizeLong

        return totalBytes.toFloat() / (1024 * 1024 * 1024)
    }

    /**
     * Get available storage in GB
     */
    fun getAvailableStorage(): Float {
        val stat = StatFs(Environment.getDataDirectory().path)
        val availableBytes = stat.availableBlocksLong * stat.blockSizeLong

        return availableBytes.toFloat() / (1024 * 1024 * 1024)
    }

    /**
     * Get formatted battery status
     */
    fun getBatteryStatus(): String {
        val level = getBatteryLevel()
        val charging = if (isCharging()) " (CHARGING)" else ""
        return "${level}%${charging}"
    }

    /**
     * Get formatted RAM status
     */
    fun getRamStatus(): String {
        val used = getTotalRam() - getAvailableRam()
        val total = getTotalRam()
        return "${used}MB / ${total}MB"
    }

    /**
     * Get formatted storage status
     */
    fun getStorageStatus(): String {
        val used = getTotalStorage() - getAvailableStorage()
        val total = getTotalStorage()
        return String.format("%.1fGB / %.1fGB", used, total)
    }
}

data class SystemStats(
    val batteryLevel: Int = 0,
    val isCharging: Boolean = false,
    val networkType: String = "OFFLINE",
    val isNetworkConnected: Boolean = false,
    val cpuUsage: Float = 0f,
    val ramUsage: Float = 0f,
    val totalRam: Long = 0,
    val availableRam: Long = 0,
    val storageUsage: Float = 0f,
    val totalStorage: Float = 0f,
    val availableStorage: Float = 0f
)
