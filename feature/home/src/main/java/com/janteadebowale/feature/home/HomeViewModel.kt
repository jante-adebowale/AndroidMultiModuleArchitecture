package com.janteadebowale.feature.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janteadebowale.core.domain.SessionManager
import com.janteadebowale.core.domain.UserDataManager
import com.janteadebowale.core.domain.transaction.TransactionRepository
import com.janteadebowale.core.model.ThemeConfig
import com.janteadebowale.feature.home.mapper.toTransactionUi
import com.janteadebowale.feature.home.model.TransactionUi
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
class HomeViewModel(
    private val userDataManager: UserDataManager,
    private val sessionManager: SessionManager,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _homeNavChannel = Channel<HomeNavEvent>()
    val homeNavChannel = _homeNavChannel.receiveAsFlow()

    init {
        userDataManager.getUserTheme().onEach {
            _state.update {
                it.copy(selectedTheme = it.selectedTheme)
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.Logout -> {
                logout()
            }

            HomeUiEvent.Refresh -> {
                refresh()
            }

            is HomeUiEvent.ThemeChange -> {
                _state.update {
                    it.copy(selectedTheme = event.selectedTheme)
                }
                setTheme(event.selectedTheme)
            }
        }
    }

    private fun setTheme(selectedTheme: ThemeConfig) {
        viewModelScope.launch {
            userDataManager.setUserTheme(selectedTheme)
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val result = transactionRepository.fetchRecentTransaction()

            _state.update {
                it.copy(isLoading = false)
            }

            when (result) {
                is DataResult.Failure -> {
                    _homeNavChannel.send(
                        HomeNavEvent.OnError(
                            result.getError().message,
                            code = result.getError().code
                        )
                    )
                }

                is DataResult.Success -> {
                    val recentTransactionList = result.getSuccessData().map { it ->
                        it.toTransactionUi()
                    }
                    _state.update {
                        it.copy(
                            recentTransactions = recentTransactionList
                        )
                    }
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            sessionManager.clear()
            userDataManager.setLoggedIn(false)
            //  _homeNavChannel.send(HomeNavEvent.OnLogout)
        }
    }
}