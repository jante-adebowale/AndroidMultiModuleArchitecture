package com.janteadebowale.core.domain.transaction

import com.janteadebowale.core.model.transaction.RecentTransaction
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
interface TransactionRepository {
    suspend fun fetchRecentTransaction(): DataResult<List<RecentTransaction>>
}