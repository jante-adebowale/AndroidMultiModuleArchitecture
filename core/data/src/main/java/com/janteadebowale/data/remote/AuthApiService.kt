package com.janteadebowale.data.remote

import com.janteadebowale.core.model.auth.AuthMeResponse
import com.janteadebowale.core.model.auth.LoginRequest
import com.janteadebowale.core.model.auth.RegistrationRequest
import com.janteadebowale.core.model.auth.TokenResponse
import com.janteadebowale.core.network.ApiEndpoints
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
interface AuthApiService {
    @POST(ApiEndpoints.AUTH_URL)
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @POST(ApiEndpoints.AUTH_REGISTER_URL)
    suspend fun register(@Body registrationRequest: RegistrationRequest): Response<TokenResponse>

    @GET(ApiEndpoints.AUTH_ME_URL)
    suspend fun authMe(): Response<AuthMeResponse>

    @GET(ApiEndpoints.AUTH_LOGOUT_URL)
    suspend fun logout(): Response<String>
}