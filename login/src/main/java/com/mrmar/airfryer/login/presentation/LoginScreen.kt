package com.mrmar.airfryer.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrmar.airfryer.core.ui.theme.AirfryerTheme
import com.mrmar.airfryer.login.presentation.viewmodel.LoginViewModel
import com.mrmar.airfryer.login.presentation.viewmodel.contract.LoginContract

private const val LOGIN_KEY_EFFECTS = "login_effects"

@Composable
fun LoginScreen() {
    val viewModel = hiltViewModel<LoginViewModel>()
    LoginContentBuilder(viewModel)
}

@Composable
private fun LoginContentBuilder(viewModel: LoginViewModel) {
    val state = viewModel.viewState.value
    LaunchedEffect(LOGIN_KEY_EFFECTS) {
        viewModel.effect.collect {

        }
    }
    LoginContent(state, viewModel::setEvent)
}

@Composable
private fun LoginContent(
    state: LoginContract.State,
    onEventSent: (event: LoginContract.Event) -> Unit,
) {
    AirfryerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text("Hello ${state.text} \n Is loading ${state.isLoading}")
                Button(onClick = { onEventSent(LoginContract.Event.UserLogin("", "")) }) {
                    Text("Navigate back")
                }
            }
        }
    }
}