package com.janteadebowale.designsystem.component

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface ActionBarOptions {
    data object OnLogout : ActionBarOptions
    data object OnRefresh : ActionBarOptions
    data object OnThemeChange : ActionBarOptions
    data object OnSetting : ActionBarOptions
}