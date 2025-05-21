package com.janteadebowale.core.domain

import com.janteadebowale.core.model.AuthToken

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
interface SessionManager {

 suspend fun set(authToken: AuthToken?)

 suspend fun get(): AuthToken?

 suspend fun clear()
}