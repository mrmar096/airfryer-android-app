package com.mrmar.airfryer.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrmar.airfryer.core.ui.theme.AirfryerTheme
import com.mrmar.airfryer.login.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(params: String) {
    val viewModel = hiltViewModel<LoginViewModel>()
    AirfryerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text("Helo $params")
                Button(onClick = { viewModel.navigateBack() }) {
                    Text("Navigate back")
                }
            }
        }
    }
}