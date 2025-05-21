package com.janteadebowale.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janteadebowale.core.domain.UserDataManager
import com.janteadebowale.core.domain.auth.AuthRepository
import com.janteadebowale.core.model.auth.LoginRequest
import com.janteadebowale.mtm.core.common.DataResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userDataManager: UserDataManager
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()


    private val _loginNavChannel = Channel<LoginNavEvent>()
    val loginNavChannel = _loginNavChannel.receiveAsFlow()

    init {
        userDataManager.getUsername().onEach { usernameValue ->
            _state.update {
                it.copy(username = usernameValue)
            }
        }.launchIn(viewModelScope)

        state.onEach {
            _state.update {
                it.copy(canLogin = it.username.length >= 10 && it.password.length > 4)
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {
            LoginUiEvent.OnLoginClicked -> {
                login()
            }

            is LoginUiEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(password = loginUiEvent.password)
                }
            }

            is LoginUiEvent.OnUsernameChanged -> {
                _state.update {
                    it.copy(username = loginUiEvent.username)
                }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            val result = authRepository.login(
                LoginRequest(
                    username = state.value.username,
                    password = state.value.password
                )
            )

            _state.update {
                it.copy(isLoading = false)
            }

            when (result) {
                is DataResult.Failure -> {
                    _loginNavChannel.send(LoginNavEvent.Error(result.getError().message))
                }

                is DataResult.Success -> {
                    _state.update {
                        it.copy(username = "", password = "")
                    }
                    userDataManager.setLoggedIn(true)
                    userDataManager.saveUsername(username = state.value.username.trim())
                    _loginNavChannel.send(LoginNavEvent.Success)
                }
            }


        }
    }

}