package com.janteadebowale.feature.home

import com.janteadebowale.core.model.ThemeConfig

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface HomeUiEvent {
    data object Logout : HomeUiEvent
    data class  ThemeChange(val selectedTheme: ThemeConfig) : HomeUiEvent
    data object Refresh : HomeUiEvent
}