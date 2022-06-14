package com.mrmar.airfryer.login.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mrmar.airfryer.core.presentation.composables.LoadingScreen
import com.mrmar.airfryer.core.presentation.composables.TopSnackbar
import com.mrmar.airfryer.core.ui.theme.Purple700
import com.mrmar.airfryer.login.R
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
    LoadingScreen(isLoading = state.isLoading) {
        LoginContent(state, viewModel::setEvent)
    }
}

@Composable
fun LoginContent(
    state: LoginContract.State,
    onEventSent: (event: LoginContract.Event) -> Unit
) {

    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            com.mrmar.airfryer.core.R.raw.airfryer_lottie
        )
    )

    state.getErrorMessage()?.let { TopSnackbar(message = it) }

    Surface(color = Purple700, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.apply {
                fillMaxSize()
                padding(all = 20.dp)
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LottieAnimation(
                composition = lottieComposition,
                modifier = Modifier.size(150.dp),
                iterations = LottieConstants.IterateForever,
            )
            Text(
                text = stringResource(R.string.welcome),
                style = TextStyle(fontSize = 35.sp, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextField(
                label = { Text(text = stringResource(R.string.email)) },
                value = state.email.orEmpty(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { onEventSent(LoginContract.Event.EmailChanged(it)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = stringResource(R.string.password)) },
                value = state.password.orEmpty(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { onEventSent(LoginContract.Event.PasswordChanged(it)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Box(modifier = Modifier.padding(55.dp, 0.dp, 55.dp, 0.dp)) {
                Button(
                    onClick = {
                        onEventSent(
                            LoginContract.Event.UserLogin
                        )
                    },
                    enabled = state.email.orEmpty().isNotBlank() && state.password.orEmpty()
                        .isNotBlank(),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = stringResource(R.string.login).uppercase())
                }
            }
        }
    }

}