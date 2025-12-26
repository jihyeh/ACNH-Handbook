package org.jihye.acnhhb.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.jihye.acnhhb.BuildConfig
import org.jihye.acnhhb.domain.repository.SettingsRepository

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> =
        settingsRepository.isDarkTheme.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            true
        )

    val isNorth: StateFlow<Boolean> =
        settingsRepository.isNorth.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            true
        )

    // access the generated BuildConfig
    val appVersion: String = BuildConfig.APP_VERSION

    fun updateTheme(isDarkTheme: Boolean) {
        viewModelScope.launch { settingsRepository.updateDarkTheme(isDarkTheme) }
    }

    fun updateHemisphere(isNorth: Boolean) {
        viewModelScope.launch { settingsRepository.updateHemisphere(isNorth) }
    }
}
