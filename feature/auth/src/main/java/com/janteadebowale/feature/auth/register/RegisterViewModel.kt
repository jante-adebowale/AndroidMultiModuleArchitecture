package com.janteadebowale.feature.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janteadebowale.core.domain.auth.AuthRepository
import com.janteadebowale.core.model.auth.RegistrationRequest
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
class RegisterViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _registerNavChannel = Channel<RegisterNavEvent>()
    val registerNavChannel = _registerNavChannel.receiveAsFlow()

    init {
        state.onEach {
            _state.update {
                it.copy(
                    canRegister =
                        (it.name.isNotEmpty()
                                && it.phone.length >= 10
                                && it.password.isNotEmpty()
                                && (it.password == it.confirmPassword))
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(registerUiEvent: RegisterUiEvent) {
        when (registerUiEvent) {
            RegisterUiEvent.OnRegisterClicked -> {
                register()
            }

            is RegisterUiEvent.OnConfirmPasswordChanged -> {
                _state.update {
                    it.copy(confirmPassword = registerUiEvent.confirmPassword)
                }
            }

            is RegisterUiEvent.OnNameChanged -> {
                _state.update {
                    it.copy(name = registerUiEvent.name)
                }
            }

            is RegisterUiEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(password = registerUiEvent.password)
                }
            }

            is RegisterUiEvent.OnPhoneChanged -> {
                _state.update {
                    it.copy(phone = registerUiEvent.phone)
                }
            }

        }
    }

    private fun register() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }


            val result = authRepository.register(
                RegistrationRequest(
                    name = state.value.name,
                    phone = state.value.phone,
                    password = state.value.password
                )
            )


            _state.update {
                it.copy(isLoading = false)
            }

            when (result) {
                is DataResult.Failure -> {
                    _registerNavChannel.send(
                        RegisterNavEvent.Error(
                            result.getError().message
                        )
                    )
                }

                is DataResult.Success -> {
                    _state.update {
                        it.copy(
                            name = "",
                            phone = "",
                            password = "",
                            confirmPassword = ""
                        )
                    }
                    _registerNavChannel.send(RegisterNavEvent.Success("Registration Successful"))
                }
            }
        }
    }

}