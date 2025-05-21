package com.janteadebowale.core.network.refreshtoken

import com.janteadebowale.core.domain.SessionManager
import com.janteadebowale.core.domain.UserDataManager
import com.janteadebowale.core.model.AuthToken
import com.janteadebowale.core.network.ApiEndpoints
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class NetworkRequestInterceptor (
    private val sessionManager: SessionManager,
) : Interceptor, KoinComponent {
    private val tokenApiService:TokenApiService by inject()

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
        const val UNAUTHORIZED_CODE = 401
        const val FORBIDDEN_CODE = 403
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!request.url.toUrl().path.equals(ApiEndpoints.AUTH_PATH)
            && !request.url.toUrl().path.equals(ApiEndpoints.REFRESH_TOKEN_PATH)
        ) {

            val authToken = runBlocking {
                sessionManager.get()
            }


            if (authToken != null) {
                request = attachTokenToRequest(request, authToken.accessToken)
            }

            val response = chain.proceed(request)

            if (response.code == UNAUTHORIZED_CODE) {
                val originalResponseBody = response.peekBody(Long.MAX_VALUE)
                response.close() // Close previous request
                synchronized(this) {
                    val newAccessToken = refreshAccessToken(authToken)
                    if (newAccessToken != null) {
                        val newRequest = attachTokenToRequest(request, newAccessToken)
                        return chain.proceed(newRequest)
                    } else {
                        return response.newBuilder().code(FORBIDDEN_CODE)
                            .body(originalResponseBody)
                            .build()
                    }
                }
            }
            return response;
        }

        return chain.proceed(request)
    }

    private fun refreshAccessToken(authToken: AuthToken?): String? {
        return if (authToken == null) null else runBlocking {
            val header = mutableMapOf<String, String>()
            header[HEADER_AUTHORIZATION] = authToken.refreshToken
            header[HEADER_AUTHORIZATION] = "$TOKEN_TYPE ${authToken.refreshToken}"
            val tokenResponse = tokenApiService.refreshToken(header)
            if (tokenResponse.isSuccessful) {
                tokenResponse.body()?.let {
                    sessionManager.set(
                        AuthToken(
                            accessToken = it.accessToken,
                            refreshToken = it.refreshToken,
                        )
                    )
                    it.accessToken
                }
            } else null
        }

    }

    private fun attachTokenToRequest(request: Request, token: String): Request {
        return request.newBuilder()
            .removeHeader(HEADER_AUTHORIZATION)
            .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
            .build()
    }

}