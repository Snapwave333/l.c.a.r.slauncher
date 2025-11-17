package com.lcars.launcher.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcars.launcher.data.models.AppInfo
import com.lcars.launcher.data.preferences.LcarsPreferences
import com.lcars.launcher.data.repository.AppsRepository
import com.lcars.launcher.ui.theme.LcarsPaletteType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the main LCARS home screen
 */
@HiltViewModel
class LcarsHomeViewModel @Inject constructor(
    private val appsRepository: AppsRepository,
    private val preferences: LcarsPreferences
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

    init {
        loadInstalledApps()
    }

    private fun loadInstalledApps() {
        viewModelScope.launch {
            appsRepository.getInstalledApps().collectLatest { apps ->
                _installedApps.value = apps
            }
        }
    }

    fun onAppDrawerToggle() {
        _uiState.update { it.copy(isAppDrawerOpen = !it.isAppDrawerOpen) }
    }

    fun onAppDrawerClose() {
        _uiState.update { it.copy(isAppDrawerOpen = false) }
    }

    fun launchApp(packageName: String) {
        appsRepository.launchApp(packageName)
        onAppDrawerClose()
    }

    fun switchProfile(profile: LcarsPaletteType) {
        viewModelScope.launch {
            preferences.setCurrentProfileId(profile.name.lowercase())
        }
    }

    fun switchDeck(index: Int) {
        viewModelScope.launch {
            preferences.setCurrentDeckIndex(index)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun getFavoritesApps(): List<AppInfo> {
        // TODO: Implement favorites logic from database
        return _installedApps.value.take(8)
    }
}

data class HomeUiState(
    val isAppDrawerOpen: Boolean = false,
    val searchQuery: String = "",
    val isLoading: Boolean = false
)
