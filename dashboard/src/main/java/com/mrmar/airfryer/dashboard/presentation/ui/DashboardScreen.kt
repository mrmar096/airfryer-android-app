package com.mrmar.airfryer.dashboard.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrmar.airfryer.core.ui.composables.AppBarScreen
import com.mrmar.airfryer.core.ui.composables.FloatingActionButton
import com.mrmar.airfryer.core.ui.composables.LoadingScreen
import com.mrmar.airfryer.dashboard.R
import com.mrmar.airfryer.dashboard.presentation.viewmodel.DashboardViewModel
import com.mrmar.airfryer.dashboard.presentation.viewmodel.contract.DashboardContract

private const val DASHBOARD_KEY_EFFECTS = "dashboard_effects"

@Composable
fun DashboardScreen() {
    val viewModel = hiltViewModel<DashboardViewModel>()
    DashboardContentBuilder(viewModel)
}

@Composable
private fun DashboardContentBuilder(viewModel: DashboardViewModel) {
    val state = viewModel.viewState.value
    LaunchedEffect(DASHBOARD_KEY_EFFECTS) {
        viewModel.effect.collect {

        }
    }
    LoadingScreen(isLoading = state.isLoading) {
        DashboardContent(state, viewModel::setEvent)
    }
}

@Composable
fun DashboardContent(
    state: DashboardContract.State,
    onEventSent: (event: DashboardContract.Event) -> Unit
) {

    AppBarScreen(
        stringResource(R.string.dashboard_title),
        logoutButton = { onEventSent(DashboardContract.Event.Logout) },
        floatingActionButton = {
            FloatingActionButton(stringResource(R.string.start_cooking)) {
                onEventSent(DashboardContract.Event.StartCooking)
            }
        }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.dashboard_title))
        }
    }
}
