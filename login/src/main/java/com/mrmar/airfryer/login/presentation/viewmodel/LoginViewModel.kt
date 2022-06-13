package com.mrmar.airfryer.login.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import com.mrmar.airfryer.core.utils.Logger
import com.mrmar.airfryer.domain.errors.DomainError
import com.mrmar.airfryer.domain.repository.IRepository
import com.mrmar.airfryer.domain.repository.exceptions.RepositoryCoroutineHandler
import com.mrmar.airfryer.login.presentation.viewmodel.contract.LoginContract
import com.mrmar.airfryer.navigation.routes.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val repository: IRepository,
    private val resources: Resources
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
                viewModelScope.launch(RepositoryCoroutineHandler(::handleError)) {
                    setState { copy(isLoading = true, text = "Loading", error = null) }
                    Logger.debug(repository.get())
                    setState { copy(isLoading = false) }
                }
            }
        }
    }

    private fun handleError(domainError: DomainError) {
        setState { copy(isLoading = false, error = domainError.getStringResource(resources)) }
    }
}