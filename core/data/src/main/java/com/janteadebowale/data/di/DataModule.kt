package com.janteadebowale.data.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.janteadebowale.core.domain.SessionManager
import com.janteadebowale.core.domain.UserDataManager
import com.janteadebowale.core.domain.auth.AuthRepository
import com.janteadebowale.core.domain.transaction.TransactionRepository
import com.janteadebowale.core.model.Constants
import com.janteadebowale.data.remote.AuthApiService
import com.janteadebowale.data.remote.TransactionApiService
import com.janteadebowale.data.repository.AuthRepositoryImpl
import com.janteadebowale.data.repository.EncryptedSessionManager
import com.janteadebowale.data.repository.TransactionRepositoryImpl
import com.janteadebowale.data.repository.UserDataManagerImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/


fun providesAuthApiService(retrofit: Retrofit): AuthApiService =
    retrofit.create(AuthApiService::class.java)

fun providesTransactionApiService(retrofit: Retrofit): TransactionApiService =
    retrofit.create(TransactionApiService::class.java)

val dataModule = module {

    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            Constants.AUTH_TOKEN_KEY,
            MasterKey(androidApplication()),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    singleOf(::providesAuthApiService)
    singleOf(::providesTransactionApiService)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::TransactionRepositoryImpl).bind<TransactionRepository>()
    singleOf(::EncryptedSessionManager).bind<SessionManager>()
    singleOf(::UserDataManagerImpl).bind<UserDataManager>()
}