package com.janteadebowale.feature.auth.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.janteadebowale.feature.auth.login.LoginRoute
import com.janteadebowale.feature.auth.register.RegisterRoute
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
data object AuthNavigationGraph

@Serializable
private data object LoginNavigation

@Serializable
private data object RegisterNavigation

fun NavGraphBuilder.authGraph(navController: NavHostController, onNavigateToHome: () -> Unit) {
    navigation<AuthNavigationGraph>(startDestination = LoginNavigation) {
        composable<LoginNavigation>(
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(1000)
                )
            }) {
            LoginRoute(
                onRegister = {
                    navController.navigate(RegisterNavigation)
                }, onNavigateToHome = onNavigateToHome
            )
        }
        composable<RegisterNavigation>(enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(1000)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(1000)
            )
        }) {
            RegisterRoute(onNavUp = {
                navController.navigateUp()
            })
        }

    }
}



