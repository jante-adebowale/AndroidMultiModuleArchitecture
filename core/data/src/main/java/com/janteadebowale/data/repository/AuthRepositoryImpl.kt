package com.janteadebowale.data.repository

import com.janteadebowale.core.domain.SessionManager
import com.janteadebowale.core.domain.auth.AuthRepository
import com.janteadebowale.core.model.AuthToken
import com.janteadebowale.core.model.auth.AuthMeResponse
import com.janteadebowale.core.model.auth.LoginRequest
import com.janteadebowale.core.model.auth.RegistrationRequest
import com.janteadebowale.core.network.safeNetworkCall
import com.janteadebowale.data.remote.AuthApiService
import com.janteadebowale.mtm.core.common.DataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val coroutineScope: CoroutineScope,
    private val sessionManager: SessionManager
) : AuthRepository {

    override suspend fun login(loginRequest: LoginRequest): DataResult<Unit> {
        val loginResponse = safeNetworkCall {
            authApiService.login(loginRequest)
        }
        if (loginResponse.isFailure()) {
            return DataResult.Failure(loginResponse.getError())
        }
        coroutineScope.launch {
            val result = loginResponse.getSuccessData()
            sessionManager.set(
                AuthToken(
                    accessToken = result.accessToken,
                    refreshToken = result.refreshToken
                )
            )
        }
        return DataResult.Success(Unit)
    }

    override suspend fun register(registrationRequest: RegistrationRequest): DataResult<Unit> {
        val response = safeNetworkCall {
            authApiService.register(registrationRequest)
        }

        if (response.isFailure()) {
            return DataResult.Failure(response.getError())
        }
        return DataResult.Success(Unit)
    }

    override suspend fun authMe(): DataResult<AuthMeResponse> {
        val response = safeNetworkCall {
            authApiService.authMe()
        }
        if (response.isFailure()) {
            return DataResult.Failure(response.getError())
        }
        return DataResult.Success(response.getSuccessData())
    }


}