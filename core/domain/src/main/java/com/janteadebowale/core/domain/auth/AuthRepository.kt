package com.janteadebowale.core.domain.auth

import com.janteadebowale.core.model.auth.AuthMeResponse
import com.janteadebowale.core.model.auth.LoginRequest
import com.janteadebowale.core.model.auth.RegistrationRequest
import com.janteadebowale.mtm.core.common.DataResult

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
interface AuthRepository {

    suspend fun login(loginRequest: LoginRequest): DataResult<Unit>

    suspend fun register(registrationRequest: RegistrationRequest): DataResult<Unit>

    suspend fun authMe(): DataResult<AuthMeResponse>

}