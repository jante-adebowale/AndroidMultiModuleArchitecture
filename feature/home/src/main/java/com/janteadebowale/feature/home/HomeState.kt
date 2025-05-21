package com.janteadebowale.feature.home

import com.janteadebowale.core.model.ThemeConfig
import com.janteadebowale.feature.home.model.TransactionUi

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
data class HomeState(
    val isLoading: Boolean = false,
    val name: String = "",
    val phone: String = "",
    val sessionId: String = "",
    val isError: Boolean = false,
    val selectedTheme: ThemeConfig = ThemeConfig.LIGHT,
    val recentTransactions : List<TransactionUi> = emptyList()
)
