package com.mrmar.airfryer.login.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import com.mrmar.airfryer.login.presentation.viewmodel.contract.LoginContract
import com.mrmar.airfryer.navigation.routes.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    stateHandle: SavedStateHandle
) : BaseViewModel<
        LoginContract.Event,
        LoginContract.State,
        LoginContract.Effect>(stateHandle) {


    override fun setInitialState(): LoginContract.State {
        return LoginContract.State(text = LoginRoute.resolveArguments(stateHandle))
    }

    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.UserLogin -> {
                viewModelScope.launch {
                    setState { copy(isLoading = true, text = "Loading") }
                    delay(2000L)
                    setState { copy(isLoading = false, text = "HideLoading") }
                }
            }
        }
    }
}