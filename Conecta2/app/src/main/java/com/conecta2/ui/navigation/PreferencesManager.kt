package com.conecta2.ui.navigation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.conecta2.data.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "conecta2_prefs")

object PreferencesManager {
    private val PROFILE_KEY = stringPreferencesKey("user_profile")
    private val THEME_KEY = stringPreferencesKey("theme_mode")
    
    suspend fun saveProfile(context: Context, profile: UserProfile) {
        context.dataStore.edit { prefs ->
            prefs[PROFILE_KEY] = profile.name
        }
    }
    
    fun getProfile(context: Context): Flow<UserProfile?> {
        return context.dataStore.data.map { prefs ->
            prefs[PROFILE_KEY]?.let { UserProfile.valueOf(it) }
        }
    }
    
    suspend fun clearAllData(context: Context) {
        context.dataStore.edit { it.clear() }
    }
    
    suspend fun saveThemeMode(context: Context, mode: String) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = mode
        }
    }
    
    fun getThemeMode(context: Context): Flow<String> {
        return context.dataStore.data.map { prefs ->
            prefs[THEME_KEY] ?: "system"
        }
    }
}
