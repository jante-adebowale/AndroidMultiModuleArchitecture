package com.janteadebowale.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.janteadebowale.core.domain.UserDataManager
import com.janteadebowale.core.model.Constants
import com.janteadebowale.core.model.ThemeConfig
import com.janteadebowale.core.model.UserConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class UserDataManagerImpl(
    private val application: Application
) : UserDataManager {

    override suspend fun saveUsername(username: String) {
        application.dataStore.edit { it ->
            it[PreferenceKeys.USERNAME] = username
        }
    }

    override fun getUsername(): Flow<String> {
        return application.dataStore.data.map {
            it[PreferenceKeys.USERNAME] ?: ""
        }
    }

    override suspend fun setLoggedIn(value: Boolean) {
        application.dataStore.edit { it ->
            it[PreferenceKeys.LOGGEDIN] = value
        }
    }

    override suspend fun setUserTheme(themeConfig: ThemeConfig) {
        application.dataStore.edit { appearancePref ->
            appearancePref[PreferenceKeys.THEME] = themeConfig.name
        }
    }

    override fun getUserTheme(): Flow<ThemeConfig> {
        return application.dataStore.data.map { theme ->
            ThemeConfig.valueOf(theme[PreferenceKeys.THEME] ?: ThemeConfig.LIGHT.name)
        }
    }

    override fun getSystemUser(): Flow<UserConfig> {
        return application.dataStore.data.map {
            UserConfig(
                it[PreferenceKeys.USERNAME] ?: "",
                it[PreferenceKeys.LOGGEDIN] ?: false,
                ThemeConfig.valueOf(it[PreferenceKeys.THEME] ?: ThemeConfig.LIGHT.name)
            )
        }
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)

    object PreferenceKeys {
        val USERNAME = stringPreferencesKey(name = Constants.USERNAME)
        val LOGGEDIN = booleanPreferencesKey(name = Constants.LOGGEDIN)
        val THEME = stringPreferencesKey(name = Constants.THEME)
    }
}