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

enum class NetworkError(val message: String) {
 NO_INTERNET("No Internet connection available"),
 INVALID_CREDENTIAL("Invalid username or password"),
 CONNECT_TIME_OUT("Internet Unavailable."),
 SOCKET_TIME_OUT("Server Unavailable.."),
 UNSTABLE_CONNECTION("Connectivity Error. Check Internet Connection"),
 PROCESS_FAILED("Failed to process request!"),
 HTTP_ERROR("Unsuccessful Server Request"),
 INVALID_BODY("Invalid Request Body!")
}

data class DynamicError(val message: String, val code: String = "")