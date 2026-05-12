package com.arcactions.data

import androidx.datastore.core.DataStore
import com.arcactions.domain.models.*

class PreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<UserPreferences>
) : PreferencesRepository {
    override val userPreferences: DataStore<UserPreferences> = dataStore

    override suspend fun updateOrbConfig(config: OrbConfig) {
        dataStore.updateData { it.copy(orbConfig = config) }
    }

    override suspend fun updateGestureSlot(slot: GestureSlot) {
        // Implementation
    }
}