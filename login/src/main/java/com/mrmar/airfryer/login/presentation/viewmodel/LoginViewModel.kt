package com.mrmar.airfryer.login.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import com.mrmar.airfryer.core.utils.validateEmail
import com.mrmar.airfryer.domain.errors.DomainError
import com.mrmar.airfryer.domain.repository.exceptions.RepositoryCoroutineHandler
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import com.mrmar.airfryer.login.R
import com.mrmar.airfryer.login.presentation.viewmodel.contract.LoginContract
import com.mrmar.airfryer.navigation.routes.DashboardRoute
import com.mrmar.airfryer.navigation.routes.LoginRouteError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val loginRepository: LoginRepository,
    private val resources: Resources
) : BaseViewModel<
        LoginContract.Event,
        LoginContract.State,
        LoginContract.Effect>(stateHandle) {


    override fun setInitialState(): LoginContract.State {
        return LoginContract.State(error = LoginRouteError.resolveArguments(stateHandle))
    }

    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.UserLogin -> {
                viewModelScope.launch(RepositoryCoroutineHandler(::handleError)) {
                    setState { copy(isLoading = true).clearErrors() }
                    validateData(
                        state.email.orEmpty(),
                        state.password.orEmpty()
                    ) { email, password ->
                        loginRepository.doLogin(email, password)
                        goToDashboard()
                    }
                    setState { copy(isLoading = false) }
                }
            }
            is LoginContract.Event.EmailChanged -> setState { copy(email = event.email).clearErrors() }
            is LoginContract.Event.PasswordChanged -> setState { copy(password = event.password).clearErrors() }
        }
    }

    private fun goToDashboard() {
        router.navigate(DashboardRoute())
    }

    private suspend fun validateData(
        email: String,
        password: String,
        validate: suspend (String, String) -> Unit
    ) {
        if (validateEmail(email)) {
            validate(email, password)
        } else {
            setState { copy(errorEmail = resources.getString(R.string.wrong_email)) }
        }
    }

    private fun handleError(domainError: DomainError) {
        setState { copy(isLoading = false, error = domainError.getStringResource(resources)) }
    }
}