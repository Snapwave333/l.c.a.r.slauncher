package com.lcars.launcher.services

import android.content.Context
import com.lcars.launcher.data.local.dao.*
import com.lcars.launcher.data.preferences.LcarsPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Backup and restore service for all launcher data
 */
@Singleton
class BackupRestoreService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val deckDao: DeckDao,
    private val profileDao: ProfileDao,
    private val panelConfigDao: PanelConfigDao,
    private val gestureMappingDao: GestureMappingDao,
    private val voiceCommandDao: VoiceCommandDao,
    private val preferences: LcarsPreferences
) {

    private val json = Json { prettyPrint = true }

    /**
     * Create full backup of all launcher data
     */
    suspend fun createBackup(): BackupData {
        val decks = deckDao.getAllDecks().first()
        val profiles = profileDao.getAllProfiles().first()
        val gestures = gestureMappingDao.getAllGestureMappings().first()
        val voiceCommands = voiceCommandDao.getAllVoiceCommands().first()

        // Get all panels for all decks
        val allPanels = mutableListOf<com.lcars.launcher.data.local.entities.PanelConfigEntity>()
        for (deck in decks) {
            val panels = panelConfigDao.getPanelsByDeckId(deck.id).first()
            allPanels.addAll(panels)
        }

        // Get preferences
        val prefs = BackupPreferences(
            currentProfileId = preferences.currentProfileId.first(),
            currentDeckIndex = preferences.currentDeckIndex.first(),
            soundEnabled = preferences.soundEnabled.first(),
            hapticsEnabled = preferences.hapticsEnabled.first(),
            immersiveMode = preferences.immersiveMode.first(),
            performanceMode = preferences.performanceMode.first(),
            showAppLabels = preferences.showAppLabels.first(),
            voiceCommandsEnabled = preferences.voiceCommandsEnabled.first(),
            gestureNavigationEnabled = preferences.gestureNavigationEnabled.first()
        )

        return BackupData(
            version = 1,
            timestamp = System.currentTimeMillis(),
            decks = decks,
            profiles = profiles,
            panels = allPanels,
            gestureMappings = gestures,
            voiceCommands = voiceCommands,
            preferences = prefs
        )
    }

    /**
     * Export backup to JSON file
     */
    suspend fun exportBackupToFile(fileName: String = "lcars_backup.json"): File {
        val backup = createBackup()
        val backupJson = json.encodeToString(backup)

        val backupDir = File(context.getExternalFilesDir(null), "backups")
        if (!backupDir.exists()) {
            backupDir.mkdirs()
        }

        val backupFile = File(backupDir, fileName)
        backupFile.writeText(backupJson)

        return backupFile
    }

    /**
     * Restore from backup data
     */
    suspend fun restoreFromBackup(backupData: BackupData): RestoreResult {
        return try {
            // Restore decks
            deckDao.insertDecks(backupData.decks)

            // Restore profiles
            profileDao.insertProfiles(backupData.profiles)

            // Restore panels
            panelConfigDao.insertPanels(backupData.panels)

            // Restore gesture mappings
            gestureMappingDao.insertGestureMappings(backupData.gestureMappings)

            // Restore voice commands
            voiceCommandDao.insertVoiceCommands(backupData.voiceCommands)

            // Restore preferences
            preferences.setCurrentProfileId(backupData.preferences.currentProfileId)
            preferences.setCurrentDeckIndex(backupData.preferences.currentDeckIndex)
            preferences.setSoundEnabled(backupData.preferences.soundEnabled)
            preferences.setHapticsEnabled(backupData.preferences.hapticsEnabled)
            preferences.setImmersiveMode(backupData.preferences.immersiveMode)
            preferences.setPerformanceMode(backupData.preferences.performanceMode)
            preferences.setShowAppLabels(backupData.preferences.showAppLabels)
            preferences.setVoiceCommandsEnabled(backupData.preferences.voiceCommandsEnabled)
            preferences.setGestureNavigationEnabled(backupData.preferences.gestureNavigationEnabled)

            RestoreResult.Success
        } catch (e: Exception) {
            RestoreResult.Error(e.message ?: "Unknown error")
        }
    }

    /**
     * Import backup from JSON file
     */
    suspend fun importBackupFromFile(file: File): RestoreResult {
        return try {
            val backupJson = file.readText()
            val backupData = json.decodeFromString<BackupData>(backupJson)
            restoreFromBackup(backupData)
        } catch (e: Exception) {
            RestoreResult.Error("Failed to import backup: ${e.message}")
        }
    }

    /**
     * List available backups
     */
    fun listBackups(): List<File> {
        val backupDir = File(context.getExternalFilesDir(null), "backups")
        if (!backupDir.exists()) return emptyList()

        return backupDir.listFiles()?.filter { it.extension == "json" }?.sortedByDescending { it.lastModified() } ?: emptyList()
    }
}

@Serializable
data class BackupData(
    val version: Int,
    val timestamp: Long,
    val decks: List<com.lcars.launcher.data.local.entities.DeckEntity>,
    val profiles: List<com.lcars.launcher.data.local.entities.ProfileEntity>,
    val panels: List<com.lcars.launcher.data.local.entities.PanelConfigEntity>,
    val gestureMappings: List<com.lcars.launcher.data.local.entities.GestureMappingEntity>,
    val voiceCommands: List<com.lcars.launcher.data.local.entities.VoiceCommandEntity>,
    val preferences: BackupPreferences
)

@Serializable
data class BackupPreferences(
    val currentProfileId: String,
    val currentDeckIndex: Int,
    val soundEnabled: Boolean,
    val hapticsEnabled: Boolean,
    val immersiveMode: Boolean,
    val performanceMode: Boolean,
    val showAppLabels: Boolean,
    val voiceCommandsEnabled: Boolean,
    val gestureNavigationEnabled: Boolean
)

sealed class RestoreResult {
    object Success : RestoreResult()
    data class Error(val message: String) : RestoreResult()
}
