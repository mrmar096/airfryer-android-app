package com.mrmar.airfryer.main.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import com.mrmar.airfryer.core.utils.Logger
import com.mrmar.airfryer.domain.errors.DomainError
import com.mrmar.airfryer.domain.repository.exceptions.RepositoryCoroutineHandler
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import com.mrmar.airfryer.main.viewmodel.contract.MainContract
import com.mrmar.airfryer.navigation.routes.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val repository: LoginRepository
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>(stateHandle) {

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch(RepositoryCoroutineHandler(::handleError)) {
            if (repository.isLoggedIn()) goToDashBoard() else goToLogin()
        }
    }

    override fun setInitialState() = MainContract.State

    override fun handleEvents(event: MainContract.Event) {

    }

    private fun handleError(domainError: DomainError) {
        goToLogin()
    }

    private fun goToDashBoard() {
        //TODO create and go to Dashboard
        Logger.debug("Going to dashboard")
    }

    private fun goToLogin() {
        router.navigate(LoginRoute())
    }


}