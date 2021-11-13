package com.lucascabral.marvelsuperheroes.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "character_preferences")

class DataStoreRepository(
    private val context: Context
) {

    private val dataStore = context.dataStore
}