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
sealed interface RegisterNavEvent {
    data class Success(val message: String) : RegisterNavEvent
    data class Error(val errorMessage: String) : RegisterNavEvent
}