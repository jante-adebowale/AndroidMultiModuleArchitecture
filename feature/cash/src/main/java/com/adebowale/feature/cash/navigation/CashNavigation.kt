package com.adebowale.feature.cash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.adebowale.feature.cash.CashRoute
import kotlinx.serialization.Serializable

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

@Serializable
data object CashNavigation

fun NavController.navigateToCash() {
     navigate(CashNavigation)
}

fun NavGraphBuilder.cashScreen(onBackClick: () -> Unit){
    composable<CashNavigation> {
        CashRoute(onBackClick = onBackClick)
    }
}