package com.janteadebowale.data.remote

import com.janteadebowale.core.model.auth.AuthMeResponse
import com.janteadebowale.core.model.transaction.RecentTransaction
import com.janteadebowale.core.network.ApiEndpoints
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
interface TransactionApiService {
 @GET(ApiEndpoints.RECENT_TRANSACTION_URL)
 suspend fun getRecentTransaction(): Response<List<RecentTransaction>>
}