package com.janteadebowale.feature.home

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface HomeNavEvent {
    data object OnLogout : HomeNavEvent
    data class OnError(val errorMessage:String,val code:String = "") : HomeNavEvent
}