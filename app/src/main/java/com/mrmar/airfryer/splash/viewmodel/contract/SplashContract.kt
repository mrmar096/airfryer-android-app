package com.mrmar.airfryer.splash.viewmodel.contract

import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewEvent
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewSideEffect
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewState

class SplashContract {
    sealed class Event : ViewEvent

    object State : ViewState {
        override val isLoading: Boolean
            get() = false
    }

    object Effect : ViewSideEffect
}