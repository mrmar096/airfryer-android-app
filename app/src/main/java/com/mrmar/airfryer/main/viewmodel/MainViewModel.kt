package com.mrmar.airfryer.main.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import com.mrmar.airfryer.main.viewmodel.contract.MainContract
import com.mrmar.airfryer.navigation.routes.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    stateHandle: SavedStateHandle
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>(stateHandle) {

    override fun setInitialState() = MainContract.State

    private fun navigate() {
        router.navigate(LoginRoute("Mario"))
    }

    override fun handleEvents(event: MainContract.Event) {
        when (event) {
            MainContract.Event.Login -> navigate()
        }
    }

}