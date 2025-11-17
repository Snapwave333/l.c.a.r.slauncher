package com.lcars.launcher.services

import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraManager
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Quick access service for flashlight, camera, WiFi, etc.
 */
@Singleton
class QuickAccessService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    private var cameraId: String? = null
    private var isFlashlightOn = false

    init {
        try {
            cameraId = cameraManager.cameraIdList.firstOrNull()
        } catch (e: Exception) {
            // Handle camera not available
        }
    }

    /**
     * Toggle flashlight on/off
     */
    fun toggleFlashlight(): Boolean {
        return try {
            cameraId?.let { id ->
                isFlashlightOn = !isFlashlightOn
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cameraManager.setTorchMode(id, isFlashlightOn)
                }
                true
            } ?: false
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Turn flashlight on
     */
    fun flashlightOn(): Boolean {
        return try {
            cameraId?.let { id ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cameraManager.setTorchMode(id, true)
                    isFlashlightOn = true
                }
                true
            } ?: false
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Turn flashlight off
     */
    fun flashlightOff(): Boolean {
        return try {
            cameraId?.let { id ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cameraManager.setTorchMode(id, false)
                    isFlashlightOn = false
                }
                true
            } ?: false
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Launch camera app
     */
    fun launchCamera() {
        try {
            val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Handle camera app not available
        }
    }

    /**
     * Toggle WiFi on/off
     */
    @Suppress("DEPRECATION")
    fun toggleWifi(): Boolean {
        return try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                // For Android 9 and below
                wifiManager.isWifiEnabled = !wifiManager.isWifiEnabled
                true
            } else {
                // For Android 10+, open WiFi settings
                openWifiSettings()
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Open WiFi settings
     */
    fun openWifiSettings() {
        try {
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Handle settings not available
        }
    }

    /**
     * Open Bluetooth settings
     */
    fun openBluetoothSettings() {
        try {
            val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Handle settings not available
        }
    }

    /**
     * Open device settings
     */
    fun openSettings() {
        try {
            val intent = Intent(Settings.ACTION_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Handle settings not available
        }
    }

    /**
     * Open display settings
     */
    fun openDisplaySettings() {
        try {
            val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Handle settings not available
        }
    }

    /**
     * Get WiFi status
     */
    fun isWifiEnabled(): Boolean {
        return try {
            wifiManager.isWifiEnabled
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Get flashlight status
     */
    fun isFlashlightOn(): Boolean {
        return isFlashlightOn
    }
}
