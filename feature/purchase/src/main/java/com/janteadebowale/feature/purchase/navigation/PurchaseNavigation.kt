package com.janteadebowale.feature.purchase.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.janteadebowale.feature.purchase.PurchaseRoute
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
internal data object PurchaseNavigation

fun NavController.navigateToPurchase() {
    navigate(PurchaseNavigation)
}


fun NavGraphBuilder.purchaseScreen(onBackClick: () -> Unit) {
    composable<PurchaseNavigation> {
        PurchaseRoute(onBackClick = {
            onBackClick()
        })
    }
}