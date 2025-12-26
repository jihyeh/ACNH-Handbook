package org.jihye.acnhhb.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val isDarkTheme: Flow<Boolean>
    val isNorth: Flow<Boolean>

    suspend fun updateDarkTheme(isDarkTheme: Boolean)
    suspend fun updateHemisphere(isNorth: Boolean)
}
