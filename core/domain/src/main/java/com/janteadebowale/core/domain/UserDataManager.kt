package com.janteadebowale.core.domain

import com.janteadebowale.core.model.ThemeConfig
import com.janteadebowale.core.model.UserConfig
import kotlinx.coroutines.flow.Flow

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
interface UserDataManager {

    suspend fun saveUsername(username: String)

    fun getUsername(): Flow<String>

    suspend fun setLoggedIn(value: Boolean)

    suspend fun setUserTheme(themeConfig: ThemeConfig)

    fun getUserTheme(): Flow<ThemeConfig>

    fun getSystemUser(): Flow<UserConfig>
}