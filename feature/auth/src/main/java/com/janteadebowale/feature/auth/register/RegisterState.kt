package com.janteadebowale.feature.auth.register

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

data class RegisterState(
    val isLoading:Boolean = false,
    var canRegister: Boolean = false,
    val name:String = "",
    val phone:String = "",
    val password:String = "",
    val confirmPassword:String = ""
)
