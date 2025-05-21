package com.janteadebowale.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.janteadebowale.feature.home.HomeRoute
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
data object HomeNavigation

fun NavGraphBuilder.homeScreen(
    onLogout: () -> Unit,
    onPurchaseClick: () -> Unit,
    onCashClick: () -> Unit
) {
    composable<HomeNavigation> {
        HomeRoute(onLogout = onLogout, onPurchaseClick = onPurchaseClick, onCashClick = onCashClick)
    }
}