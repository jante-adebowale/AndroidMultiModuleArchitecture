package com.janteadebowale.androidmultimodulearchitecture

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.adebowale.feature.cash.navigation.cashScreen
import com.adebowale.feature.cash.navigation.navigateToCash
import com.janteadebowale.feature.auth.navigation.AuthNavigationGraph
import com.janteadebowale.feature.auth.navigation.authGraph
import com.janteadebowale.feature.home.navigation.HomeNavigation
import com.janteadebowale.feature.home.navigation.homeScreen
import com.janteadebowale.feature.purchase.navigation.navigateToPurchase
import com.janteadebowale.feature.purchase.navigation.purchaseScreen

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    isLoggedIn: Boolean = true
) {
    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn) HomeNavigation else AuthNavigationGraph
    ) {
        authGraph(navController = navHostController, onNavigateToHome = {
            navHostController.navigate(HomeNavigation) {
                popUpTo(AuthNavigationGraph) {
                    inclusive = true
                }
            }
        })

        homeScreen(onLogout = {
            navHostController.navigate(AuthNavigationGraph) {
                popUpTo(HomeNavigation) {
                    inclusive = true
                }
            }
        }, onPurchaseClick = {
            navHostController.navigateToPurchase()
        }, onCashClick = {
            navHostController.navigateToCash()
        })

        purchaseScreen (onBackClick = {
            navHostController.navigateUp()
        })

        cashScreen (onBackClick = {
            navHostController.navigateUp()
        })

    }
}