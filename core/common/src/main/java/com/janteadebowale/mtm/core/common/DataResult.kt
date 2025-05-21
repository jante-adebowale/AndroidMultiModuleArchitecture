package com.janteadebowale.mtm.core.common

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface DataResult<out T> {

    data class Success<T>(val data: T) : DataResult<T>

    data class Failure(val dataError: DynamicError) :
        DataResult<Nothing>

    fun isSuccess(): Boolean = this is Success

    fun isFailure(): Boolean = this is Failure

    fun getSuccessData() = (this as Success).data

    fun getError() = (this as Failure).dataError

}