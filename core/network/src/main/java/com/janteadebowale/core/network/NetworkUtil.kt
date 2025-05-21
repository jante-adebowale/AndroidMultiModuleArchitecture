package com.janteadebowale.core.network

import com.janteadebowale.mtm.core.common.DataResult
import com.janteadebowale.mtm.core.common.DynamicError
import com.janteadebowale.mtm.core.common.NetworkError
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import kotlin.coroutines.cancellation.CancellationException

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

object ApiEndpoints {
    const val AUTH_URL = "auth/login"
    const val AUTH_REFRESH_URL = "auth/refresh-token"
    const val AUTH_ME_URL = "auth/me"
    const val RECENT_TRANSACTION_URL = "transactions/recent"
    const val AUTH_REGISTER_URL = "auth/register"
    const val AUTH_LOGOUT_URL = "auth/logout"
    const val AUTH_PATH = "/api/auth/login"
    const val REFRESH_TOKEN_PATH = "/api/auth/refresh-token"
}

inline fun <T> safeNetworkCall(executeRequest: () -> Response<T>): DataResult<T> {
    val response = try {
        executeRequest()
    } catch (ioException: IOException) {
        return DataResult.Failure(DynamicError(NetworkError.UNSTABLE_CONNECTION.message))
    } catch (socketTime: SocketTimeoutException) {
        return DataResult.Failure(DynamicError(NetworkError.SOCKET_TIME_OUT.message))
    } catch (socketTime: ConnectException) {
        return DataResult.Failure(DynamicError(NetworkError.CONNECT_TIME_OUT.message))
    } catch (http: HttpException) {
        return DataResult.Failure(DynamicError(NetworkError.HTTP_ERROR.message))
    } catch (exception: Exception) {
        exception.printStackTrace()
        if (exception is CancellationException) throw exception
        return DataResult.Failure(DynamicError(NetworkError.PROCESS_FAILED.message))
    }

    return if (response.isSuccessful) {
        response.body()?.let {
            DataResult.Success(it)
        } ?: DataResult.Failure(DynamicError(NetworkError.INVALID_BODY.message))
    } else {
        val code = response.code().toString()
        val errorString = response.errorBody()?.string()
        DataResult.Failure(DynamicError("$code: $errorString", code = code) )
    }

}