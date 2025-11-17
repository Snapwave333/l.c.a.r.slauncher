package com.lcars.launcher.services

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Sound effects engine for LCARS UI interactions
 */
@Singleton
class SoundEffectsService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val soundPool: SoundPool
    private val sounds = mutableMapOf<SoundEffect, Int>()

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(audioAttributes)
            .build()

        // Load sound effects (we'll use system sounds for now)
        // In a production app, you'd load custom LCARS sound files from res/raw
        loadSystemSounds()
    }

    private fun loadSystemSounds() {
        // For now, we'll generate simple beep sounds programmatically
        // In production, load actual LCARS sound files from res/raw/
        // sounds[SoundEffect.BUTTON_CLICK] = soundPool.load(context, R.raw.lcars_button_click, 1)
        // sounds[SoundEffect.PANEL_OPEN] = soundPool.load(context, R.raw.lcars_panel_open, 1)
        // etc.
    }

    /**
     * Play button click sound
     */
    fun playButtonClick() {
        playSoundEffect(SoundEffect.BUTTON_CLICK)
    }

    /**
     * Play panel transition sound
     */
    fun playPanelTransition() {
        playSoundEffect(SoundEffect.PANEL_TRANSITION)
    }

    /**
     * Play deck swap sound
     */
    fun playDeckSwap() {
        playSoundEffect(SoundEffect.DECK_SWAP)
    }

    /**
     * Play profile change sound
     */
    fun playProfileChange() {
        playSoundEffect(SoundEffect.PROFILE_CHANGE)
    }

    /**
     * Play alert sound (Red Alert mode)
     */
    fun playAlert() {
        playSoundEffect(SoundEffect.ALERT)
    }

    /**
     * Play app launch sound
     */
    fun playAppLaunch() {
        playSoundEffect(SoundEffect.APP_LAUNCH)
    }

    /**
     * Play success sound
     */
    fun playSuccess() {
        playSoundEffect(SoundEffect.SUCCESS)
    }

    /**
     * Play error sound
     */
    fun playError() {
        playSoundEffect(SoundEffect.ERROR)
    }

    /**
     * Play confirmation sound
     */
    fun playConfirm() {
        playSoundEffect(SoundEffect.CONFIRM)
    }

    private fun playSoundEffect(effect: SoundEffect, volume: Float = 1.0f) {
        sounds[effect]?.let { soundId ->
            soundPool.play(soundId, volume, volume, 1, 0, 1.0f)
        }
    }

    fun release() {
        soundPool.release()
    }

    enum class SoundEffect {
        BUTTON_CLICK,
        PANEL_TRANSITION,
        DECK_SWAP,
        PROFILE_CHANGE,
        ALERT,
        APP_LAUNCH,
        SUCCESS,
        ERROR,
        CONFIRM
    }
}
