package com.mrmar.airfryer.login.presentation.viewmodel.contract

import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewEvent
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewSideEffect
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewState

class LoginContract {
    sealed class Event : ViewEvent {
        data class UserLogin(val email: String, val password: String) : Event()
    }

    data class State(
        override val isLoading: Boolean = false,
        val text: String,
        val error: String? = null
    ) : ViewState {
        override fun getErrorMessage() = error
    }

    sealed class Effect : ViewSideEffect {

    }
}