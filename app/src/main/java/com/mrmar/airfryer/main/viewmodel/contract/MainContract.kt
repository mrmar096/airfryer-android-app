package com.mrmar.airfryer.main.viewmodel.contract

import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewEvent
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewSideEffect
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewState

class MainContract {
    sealed class Event : ViewEvent {
        object Login : Event()
    }

    object State : ViewState

    object Effect : ViewSideEffect
}