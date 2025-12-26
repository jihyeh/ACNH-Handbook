package org.jihye.acnhhb.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.jihye.acnhhb.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : SettingsRepository {

    private object Keys {
        val THEME = booleanPreferencesKey("theme")
        val HEMISPHERE = booleanPreferencesKey("hemisphere")
    }

    override val isDarkTheme: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[Keys.THEME] ?: true
    }

    override val isNorth: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[Keys.HEMISPHERE] ?: true
    }

    override suspend fun updateDarkTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences -> preferences[Keys.THEME] = isDarkTheme }
    }

    override suspend fun updateHemisphere(isNorth: Boolean) {
        dataStore.edit { preferences -> preferences[Keys.HEMISPHERE] = isNorth }
    }
}
