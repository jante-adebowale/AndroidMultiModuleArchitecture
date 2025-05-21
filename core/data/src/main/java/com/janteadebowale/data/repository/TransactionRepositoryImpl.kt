package com.janteadebowale.data.repository

import com.janteadebowale.core.domain.transaction.TransactionRepository
import com.janteadebowale.core.model.transaction.RecentTransaction
import com.janteadebowale.core.network.safeNetworkCall
import com.janteadebowale.data.remote.TransactionApiService
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
class TransactionRepositoryImpl(
    private val transactionApiService: TransactionApiService
) : TransactionRepository {
    override suspend fun fetchRecentTransaction(): DataResult<List<RecentTransaction>> {
        val response = safeNetworkCall {
            transactionApiService.getRecentTransaction()
        }
        if (response.isFailure()) {
            return DataResult.Failure(response.getError())
        }
        return DataResult.Success(response.getSuccessData())
    }
}