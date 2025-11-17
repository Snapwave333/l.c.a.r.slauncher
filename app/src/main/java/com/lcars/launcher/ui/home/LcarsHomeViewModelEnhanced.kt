package com.lcars.launcher.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcars.launcher.data.models.AppInfo
import com.lcars.launcher.data.preferences.LcarsPreferences
import com.lcars.launcher.data.repository.AppsRepository
import com.lcars.launcher.data.repository.DeckRepository
import com.lcars.launcher.services.*
import com.lcars.launcher.ui.theme.LcarsPaletteType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Enhanced ViewModel for the main LCARS home screen with full roadmap features
 */
@HiltViewModel
class LcarsHomeViewModelEnhanced @Inject constructor(
    private val appsRepository: AppsRepository,
    private val deckRepository: DeckRepository,
    private val preferences: LcarsPreferences,
    private val gestureService: GestureDetectionService,
    private val hapticService: HapticFeedbackService,
    private val soundService: SoundEffectsService,
    private val systemMonitor: SystemMonitorService,
    private val voiceCommandService: VoiceCommandService,
    private val profileTriggerService: ProfileTriggerService,
    private val missionAutomationService: MissionAutomationService,
    private val mediaControlService: MediaControlService,
    private val quickAccessService: QuickAccessService,
    private val backupRestoreService: BackupRestoreService
) : ViewModel() {

    // UI State
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // Installed apps
    private val _installedApps = MutableStateFlow<List<AppInfo>>(emptyList())
    val installedApps: StateFlow<List<AppInfo>> = _installedApps.asStateFlow()

    // Current profile
    val currentProfile: StateFlow<LcarsPaletteType> = preferences.currentProfileId
        .map { profileId -> LcarsPaletteType.fromString(profileId.uppercase()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LcarsPaletteType.BRIDGE)

    // Current deck index
    val currentDeckIndex: StateFlow<Int> = preferences.currentDeckIndex
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // System stats
    val systemStats: StateFlow<SystemStats> = systemMonitor.systemStats

    // Media state
    val mediaState: StateFlow<MediaState> = mediaControlService.mediaState

    // Gesture events
    val gestureEvents: StateFlow<GestureEvent?> = gestureService.gestureEvents

    // Preferences
    val soundEnabled: StateFlow<Boolean> = preferences.soundEnabled
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)

    val hapticsEnabled: StateFlow<Boolean> = preferences.hapticsEnabled
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)

    init {
        loadInstalledApps()
        initializeDefaultData()
        startSystemMonitoring()
        observeGestures()
        checkProfileTriggers()
    }

    private fun loadInstalledApps() {
        viewModelScope.launch {
            appsRepository.getInstalledApps().collectLatest { apps ->
                _installedApps.value = apps
            }
        }
    }

    private fun initializeDefaultData() {
        viewModelScope.launch {
            deckRepository.initializeDefaultDecks()
            voiceCommandService.initializeDefaultVoiceCommands()
        }
    }

    private fun startSystemMonitoring() {
        viewModelScope.launch {
            while (true) {
                systemMonitor.updateSystemStats()
                delay(5000) // Update every 5 seconds
            }
        }
    }

    private fun observeGestures() {
        viewModelScope.launch {
            preferences.gestureNavigationEnabled.collect { enabled ->
                if (enabled) {
                    gestureEvents.collect { event ->
                        when (event) {
                            is GestureEvent.SwipeLeft -> nextDeck()
                            is GestureEvent.SwipeRight -> previousDeck()
                            is GestureEvent.SwipeUp -> onAppDrawerToggle()
                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun checkProfileTriggers() {
        viewModelScope.launch {
            while (true) {
                val triggeredProfile = profileTriggerService.checkTriggersAndSwitch()
                if (triggeredProfile != null) {
                    val paletteType = LcarsPaletteType.fromString(triggeredProfile)
                    switchProfile(paletteType)
                }
                delay(60000) // Check every minute
            }
        }
    }

    // App drawer functions
    fun onAppDrawerToggle() {
        _uiState.update { it.copy(isAppDrawerOpen = !it.isAppDrawerOpen) }
        if (hapticsEnabled.value) hapticService.lightClick()
    }

    fun onAppDrawerClose() {
        _uiState.update { it.copy(isAppDrawerOpen = false) }
    }

    fun launchApp(packageName: String) {
        appsRepository.launchApp(packageName)
        onAppDrawerClose()

        if (soundEnabled.value) soundService.playAppLaunch()
        if (hapticsEnabled.value) hapticService.lightClick()
    }

    // Profile and deck functions
    fun switchProfile(profile: LcarsPaletteType) {
        viewModelScope.launch {
            preferences.setCurrentProfileId(profile.name.lowercase())

            if (soundEnabled.value) soundService.playProfileChange()
            if (hapticsEnabled.value) hapticService.heavyClick()
        }
    }

    fun switchDeck(index: Int) {
        viewModelScope.launch {
            preferences.setCurrentDeckIndex(index)
        }
    }

    fun nextDeck() {
        viewModelScope.launch {
            val totalDecks = deckRepository.getDecksCount()
            val current = currentDeckIndex.value
            val next = if (current + 1 >= totalDecks) 0 else current + 1
            switchDeck(next)

            if (soundEnabled.value) soundService.playDeckSwap()
            if (hapticsEnabled.value) hapticService.mediumClick()
        }
    }

    fun previousDeck() {
        viewModelScope.launch {
            val totalDecks = deckRepository.getDecksCount()
            val current = currentDeckIndex.value
            val prev = if (current - 1 < 0) totalDecks - 1 else current - 1
            switchDeck(prev)

            if (soundEnabled.value) soundService.playDeckSwap()
            if (hapticsEnabled.value) hapticService.mediumClick()
        }
    }

    // Search function
    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    // Quick access functions
    fun toggleFlashlight() {
        quickAccessService.toggleFlashlight()
        if (hapticsEnabled.value) hapticService.lightClick()
    }

    fun launchCamera() {
        quickAccessService.launchCamera()
        if (hapticsEnabled.value) hapticService.lightClick()
    }

    fun toggleWifi() {
        quickAccessService.toggleWifi()
        if (hapticsEnabled.value) hapticService.lightClick()
    }

    // Media controls
    fun mediaPlayPause() {
        mediaControlService.playPause()
        if (hapticsEnabled.value) hapticService.lightClick()
    }

    fun mediaNext() {
        mediaControlService.nextTrack()
        if (hapticsEnabled.value) hapticService.lightClick()
    }

    fun mediaPrevious() {
        mediaControlService.previousTrack()
        if (hapticsEnabled.value) hapticService.lightClick()
    }

    // Mission automation
    fun executeMission(missionName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (val result = missionAutomationService.executeMission(missionName)) {
                is MissionResult.Success -> {
                    if (soundEnabled.value) soundService.playSuccess()
                    if (hapticsEnabled.value) hapticService.success()
                }
                else -> {
                    if (soundEnabled.value) soundService.playError()
                    if (hapticsEnabled.value) hapticService.error()
                }
            }

            _uiState.update { it.copy(isLoading = false) }
        }
    }

    // Backup/restore
    fun createBackup() {
        viewModelScope.launch {
            try {
                backupRestoreService.exportBackupToFile()
                if (soundEnabled.value) soundService.playSuccess()
                if (hapticsEnabled.value) hapticService.success()
            } catch (e: Exception) {
                if (soundEnabled.value) soundService.playError()
                if (hapticsEnabled.value) hapticService.error()
            }
        }
    }

    // Favorites
    fun getFavoritesApps(): List<AppInfo> {
        // TODO: Implement favorites logic from database
        return _installedApps.value.take(8)
    }

    // Voice commands
    fun processVoiceCommand(spokenText: String) {
        viewModelScope.launch {
            when (val result = voiceCommandService.processVoiceInput(spokenText)) {
                is VoiceCommandResult.Success -> {
                    executeVoiceCommand(result.command.actionType, result.command.actionPayload)
                }
                else -> {
                    if (soundEnabled.value) soundService.playError()
                }
            }
        }
    }

    private fun executeVoiceCommand(actionType: String, payload: String?) {
        when (actionType) {
            "OPEN_APP_DRAWER" -> onAppDrawerToggle()
            "SWITCH_PROFILE" -> payload?.let { switchProfile(LcarsPaletteType.fromString(it)) }
            "NEXT_DECK" -> nextDeck()
            "PREVIOUS_DECK" -> previousDeck()
            else -> {}
        }
    }
}

data class HomeUiState(
    val isAppDrawerOpen: Boolean = false,
    val searchQuery: String = "",
    val isLoading: Boolean = false
)
