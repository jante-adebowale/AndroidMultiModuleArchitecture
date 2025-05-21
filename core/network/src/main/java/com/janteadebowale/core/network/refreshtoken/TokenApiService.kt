package com.janteadebowale.core.network.refreshtoken

import com.janteadebowale.core.network.ApiEndpoints
import retrofit2.Response
import retrofit2.http.HeaderMap
import retrofit2.http.POST

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
interface TokenApiService {
    @POST(ApiEndpoints.AUTH_REFRESH_URL)
    suspend fun refreshToken(@HeaderMap headers: Map<String, String>): Response<RefreshTokenResponse>
}