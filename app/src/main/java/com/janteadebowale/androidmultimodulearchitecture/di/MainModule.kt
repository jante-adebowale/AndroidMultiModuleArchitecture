package com.janteadebowale.androidmultimodulearchitecture.di

import com.janteadebowale.androidmultimodulearchitecture.MTMApplication
import com.janteadebowale.androidmultimodulearchitecture.MainViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/************************************************************
 2025 Copyright (C), CodeInKotlin
 https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

val mainModule = module {
    single<CoroutineScope> {
        (androidApplication() as MTMApplication).applicationCoroutineScope
    }
    viewModelOf(::MainViewModel)
}