package com.arcactions.data

import androidx.datastore.core.DataStore
import com.arcactions.domain.models.UserPreferences

interface PreferencesRepository {
    val userPreferences: DataStore<UserPreferences>
    suspend fun updateOrbConfig(config: OrbConfig)
    suspend fun updateGestureSlot(slot: GestureSlot)
    // Add more update methods
}