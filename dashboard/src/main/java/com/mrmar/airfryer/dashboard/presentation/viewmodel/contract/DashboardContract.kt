package com.mrmar.airfryer.dashboard.presentation.viewmodel.contract

import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewEvent
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewSideEffect
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewState

class DashboardContract {
    sealed class Event : ViewEvent {
        object Logout : Event()
        object StartCooking : Event()
    }

    data class State(
        override val isLoading: Boolean = false,
        val error: String? = null
    ) : ViewState {

        override fun getErrorMessage() = error

        override fun clearErrors(): State {
            return copy(error = null)
        }
    }

    sealed class Effect : ViewSideEffect {

    }
}