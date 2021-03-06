package com.mrmar.airfryer.splash.viewmodel

import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import com.mrmar.airfryer.core.utils.Logger
import com.mrmar.airfryer.core.utils.delayedData
import com.mrmar.airfryer.domain.errors.DomainError
import com.mrmar.airfryer.domain.repository.exceptions.RepositoryCoroutineHandler
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import com.mrmar.airfryer.navigation.routes.LoginRoute
import com.mrmar.airfryer.navigation.routes.LoginRouteError
import com.mrmar.airfryer.splash.viewmodel.contract.SplashContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val repository: LoginRepository,
    private val resources: Resources
) : BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>(stateHandle) {

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch(RepositoryCoroutineHandler(::handleError)) {
            delayedData { repository.checkUserSession() }
            goToDashBoard()
        }
    }

    override fun setInitialState() = SplashContract.State

    override fun handleEvents(event: SplashContract.Event) {

    }

    private fun handleError(domainError: DomainError) {
        if (domainError is DomainError.SessionExpired) {
            goToLogin(domainError.getStringResource(resources))
        } else {
            goToLogin()
        }
    }

    private fun goToDashBoard() {
        //TODO create and go to Dashboard
        Logger.debug("Going to dashboard")
    }

    private fun goToLogin(error: String? = null) {
        router.navigate(error?.let { LoginRouteError(it) } ?: LoginRoute())
    }

}