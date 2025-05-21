package com.janteadebowale.feature.home.model

import androidx.compose.ui.graphics.Color

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
data class TransactionUi(
    val type: String, val amount: String, val time: String, val color: Color = Color.Green,
)
