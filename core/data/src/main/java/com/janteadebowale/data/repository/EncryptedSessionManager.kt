package com.janteadebowale.data.repository

import android.content.SharedPreferences
import com.janteadebowale.core.domain.SessionManager
import com.janteadebowale.core.model.AuthToken
import com.janteadebowale.core.model.Constants.AUTH_TOKEN_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.core.content.edit
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class EncryptedSessionManager(
    private val sharedPreferences: SharedPreferences,
) : SessionManager{
    override suspend fun set(authToken: AuthToken?) {
        withContext(Dispatchers.IO) {
            val json = Json.encodeToString(authToken)
            sharedPreferences.edit(commit = true) { putString(AUTH_TOKEN_KEY, json) }
        }
    }

    override suspend fun get(): AuthToken? {
        return withContext(Dispatchers.IO) {
            val value = sharedPreferences.getString(AUTH_TOKEN_KEY,null)
            val returnAuthValue = value?.let {
                Json.decodeFromString<AuthToken>(it)
            }
            return@withContext returnAuthValue
        }
    }

    override suspend fun clear() {
        withContext(Dispatchers.IO){
            sharedPreferences.edit(commit = true) { remove(AUTH_TOKEN_KEY) }
        }
    }
}