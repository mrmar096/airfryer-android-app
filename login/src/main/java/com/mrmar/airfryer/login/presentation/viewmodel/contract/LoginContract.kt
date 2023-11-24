package com.mrmar.airfryer.login.presentation.viewmodel.contract

import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewEvent
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewSideEffect
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewState

class LoginContract {
    sealed class Event : ViewEvent {
        data object UserLogin : Event()
        data class EmailChanged(val email: String) : Event()
        data class PasswordChanged(val password: String) : Event()
    }

    data class State(
        override val isLoading: Boolean = false,
        val email: String? = null,
        val password: String? = null,
        val errorEmail: String? = null,
        val error: String? = null
    ) : ViewState {

        override fun getErrorMessage() = error

        override fun clearErrors(): State {
            return copy(error = null, errorEmail = null)
        }
    }

    sealed class Effect : ViewSideEffect {

    }
}