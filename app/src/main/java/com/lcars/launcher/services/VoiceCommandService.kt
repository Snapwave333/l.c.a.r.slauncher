package com.lcars.launcher.services

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import com.lcars.launcher.data.local.dao.VoiceCommandDao
import com.lcars.launcher.data.local.entities.VoiceCommandEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Voice command service for local voice recognition
 */
@Singleton
class VoiceCommandService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val voiceCommandDao: VoiceCommandDao
) {

    /**
     * Process voice input and execute command
     */
    suspend fun processVoiceInput(spokenText: String): VoiceCommandResult {
        val normalizedInput = spokenText.lowercase(Locale.getDefault()).trim()

        // Get all voice commands
        val commands = voiceCommandDao.getAllVoiceCommands().first()

        // Find matching command
        val matchingCommand = commands.firstOrNull { command ->
            command.phrase.lowercase(Locale.getDefault()) in normalizedInput ||
                    normalizedInput.contains(command.phrase.lowercase(Locale.getDefault()))
        }

        return if (matchingCommand != null) {
            VoiceCommandResult.Success(matchingCommand)
        } else {
            VoiceCommandResult.NoMatch(normalizedInput)
        }
    }

    /**
     * Create speech recognition intent
     */
    fun createSpeechRecognitionIntent(): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Computer, awaiting command...")
        }
    }

    /**
     * Add default voice commands
     */
    suspend fun initializeDefaultVoiceCommands() {
        val defaultCommands = listOf(
            VoiceCommandEntity(
                id = "vc_open_apps",
                phrase = "computer open apps",
                actionType = "OPEN_APP_DRAWER",
                actionPayload = null
            ),
            VoiceCommandEntity(
                id = "vc_switch_bridge",
                phrase = "computer switch to bridge",
                actionType = "SWITCH_PROFILE",
                actionPayload = "BRIDGE"
            ),
            VoiceCommandEntity(
                id = "vc_switch_engineering",
                phrase = "computer switch to engineering",
                actionType = "SWITCH_PROFILE",
                actionPayload = "ENGINEERING"
            ),
            VoiceCommandEntity(
                id = "vc_switch_tactical",
                phrase = "computer switch to tactical",
                actionType = "SWITCH_PROFILE",
                actionPayload = "TACTICAL"
            ),
            VoiceCommandEntity(
                id = "vc_red_alert",
                phrase = "computer red alert",
                actionType = "SWITCH_PROFILE",
                actionPayload = "RED_ALERT"
            ),
            VoiceCommandEntity(
                id = "vc_next_deck",
                phrase = "computer next deck",
                actionType = "NEXT_DECK",
                actionPayload = null
            ),
            VoiceCommandEntity(
                id = "vc_prev_deck",
                phrase = "computer previous deck",
                actionType = "PREVIOUS_DECK",
                actionPayload = null
            ),
            VoiceCommandEntity(
                id = "vc_status",
                phrase = "computer status report",
                actionType = "SHOW_STATUS",
                actionPayload = null
            )
        )

        voiceCommandDao.insertVoiceCommands(defaultCommands)
    }

    /**
     * Add custom voice command
     */
    suspend fun addVoiceCommand(phrase: String, actionType: String, actionPayload: String? = null) {
        val command = VoiceCommandEntity(
            id = "vc_${System.currentTimeMillis()}",
            phrase = phrase,
            actionType = actionType,
            actionPayload = actionPayload
        )
        voiceCommandDao.insertVoiceCommand(command)
    }

    /**
     * Delete voice command
     */
    suspend fun deleteVoiceCommand(commandId: String) {
        voiceCommandDao.deleteVoiceCommandById(commandId)
    }
}

sealed class VoiceCommandResult {
    data class Success(val command: VoiceCommandEntity) : VoiceCommandResult()
    data class NoMatch(val spokenText: String) : VoiceCommandResult()
    object Error : VoiceCommandResult()
}
