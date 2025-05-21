package com.janteadebowale.androidmultimodulearchitecture

import com.janteadebowale.core.model.UserConfig

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface MainUiState {
    data object Loading : MainUiState
    data class Success(val userConfig: UserConfig) : MainUiState
}