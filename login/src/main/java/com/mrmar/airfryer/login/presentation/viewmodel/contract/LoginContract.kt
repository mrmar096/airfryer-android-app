package com.mrmar.airfryer.login.presentation.viewmodel.contract

import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewEvent
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewSideEffect
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewState

class LoginContract {
    sealed class Event : ViewEvent {
        data class UserLogin(val email: String, val password: String) : Event()
    }

    data class State(val isLoading: Boolean = false, val text: String) : ViewState

    sealed class Effect : ViewSideEffect {

    }
}