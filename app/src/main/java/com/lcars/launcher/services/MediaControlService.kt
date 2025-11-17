package com.lcars.launcher.services

import android.content.Context
import android.media.AudioManager
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.media.session.PlaybackState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Media control service for playback controls
 */
@Singleton
class MediaControlService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private val mediaSessionManager = context.getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager

    private val _mediaState = MutableStateFlow(MediaState())
    val mediaState: StateFlow<MediaState> = _mediaState.asStateFlow()

    private var activeController: MediaController? = null

    init {
        updateActiveController()
    }

    /**
     * Update active media controller
     */
    private fun updateActiveController() {
        val controllers = mediaSessionManager.getActiveSessions(null)
        activeController = controllers.firstOrNull()

        activeController?.let { controller ->
            _mediaState.value = MediaState(
                isPlaying = controller.playbackState?.state == PlaybackState.STATE_PLAYING,
                trackTitle = controller.metadata?.getString(android.media.MediaMetadata.METADATA_KEY_TITLE) ?: "Unknown",
                artist = controller.metadata?.getString(android.media.MediaMetadata.METADATA_KEY_ARTIST) ?: "Unknown",
                hasActiveSession = true
            )
        } ?: run {
            _mediaState.value = MediaState()
        }
    }

    /**
     * Play/pause toggle
     */
    fun playPause() {
        updateActiveController()
        activeController?.let { controller ->
            if (controller.playbackState?.state == PlaybackState.STATE_PLAYING) {
                controller.transportControls?.pause()
            } else {
                controller.transportControls?.play()
            }
        }
    }

    /**
     * Next track
     */
    fun nextTrack() {
        updateActiveController()
        activeController?.transportControls?.skipToNext()
    }

    /**
     * Previous track
     */
    fun previousTrack() {
        updateActiveController()
        activeController?.transportControls?.skipToPrevious()
    }

    /**
     * Get current volume level (0-100)
     */
    fun getVolumeLevel(): Int {
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        return ((currentVolume.toFloat() / maxVolume.toFloat()) * 100).toInt()
    }

    /**
     * Set volume level (0-100)
     */
    fun setVolumeLevel(level: Int) {
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val targetVolume = ((level.toFloat() / 100f) * maxVolume).toInt()
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, targetVolume, 0)
    }

    /**
     * Volume up
     */
    fun volumeUp() {
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0)
    }

    /**
     * Volume down
     */
    fun volumeDown() {
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0)
    }

    /**
     * Mute/unmute
     */
    fun toggleMute() {
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_TOGGLE_MUTE, 0)
    }
}

data class MediaState(
    val isPlaying: Boolean = false,
    val trackTitle: String = "",
    val artist: String = "",
    val hasActiveSession: Boolean = false
)
