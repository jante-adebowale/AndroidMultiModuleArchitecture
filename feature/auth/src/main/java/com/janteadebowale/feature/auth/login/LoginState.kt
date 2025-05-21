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
data class LoginState(
    val isLoading: Boolean = false,
    var canLogin: Boolean = false,
    var username: String = "",
    var password: String = "",
)
