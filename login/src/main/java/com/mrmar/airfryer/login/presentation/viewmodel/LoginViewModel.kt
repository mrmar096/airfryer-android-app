package com.mrmar.airfryer.login.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import com.mrmar.airfryer.domain.errors.DomainError
import com.mrmar.airfryer.domain.repository.exceptions.RepositoryCoroutineHandler
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import com.mrmar.airfryer.login.presentation.viewmodel.contract.LoginContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
        return LoginContract.State()
    }

    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.UserLogin -> {
                viewModelScope.launch(RepositoryCoroutineHandler(::handleError)) {
                    setState { copy(isLoading = true, error = null) }
                    delay(5000)
                    validateData(
                        state.email.orEmpty(),
                        state.password.orEmpty()
                    ) { email, password ->
                        loginRepository.doLogin(email, password)
                    }
                    setState { copy(isLoading = false) }
                }
            }
            is LoginContract.Event.EmailChanged -> setState { copy(email = event.email) }
            is LoginContract.Event.PasswordChanged -> setState { copy(password = event.password) }
        }
    }

    private fun validateData(
        email: String,
        password: String,
        validate: suspend (String, String) -> Unit
    ) {

    }

    private fun handleError(domainError: DomainError) {
        setState { copy(isLoading = false, error = domainError.getStringResource(resources)) }
    }
}