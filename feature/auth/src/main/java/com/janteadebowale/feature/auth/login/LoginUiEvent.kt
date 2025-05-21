package com.janteadebowale.feature.auth.login

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface LoginUiEvent {
    data object OnLoginClicked : LoginUiEvent
    data class OnUsernameChanged(val username: String) : LoginUiEvent
    data class OnPasswordChanged(val password: String) : LoginUiEvent
}