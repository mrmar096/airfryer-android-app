package com.mrmar.airfryer.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrmar.airfryer.core.presentation.router.Router
import com.mrmar.airfryer.core.presentation.router.routes.BackRoute
import com.mrmar.airfryer.core.ui.theme.AirfryerTheme
import com.mrmar.airfryer.login.presentation.route.LoginRouteGraph
import com.mrmar.airfryer.main.viewmodel.MainViewModel
import com.mrmar.airfryer.main.viewmodel.contract.MainContract
import com.mrmar.airfryer.navigation.routes.MainRoute

@Composable
fun MainScreen(mainRouter: Router) {
    AirfryerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()
            Box(Modifier.fillMaxSize()) {
                mainRouter.observeNavigation { route ->
                    if (route is BackRoute) {
                        navController.popBackStack()
                    } else {
                        navController.navigate(route.getUriData(), route.buildOptions())
                    }
                }
                NavHost(navController, startDestination = MainRoute.getUriData()) {
                    composable(MainRoute.getUriData()) {
                        MainContent()
                    }
                    LoginRouteGraph.build(this)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainContent() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(onClick = { mainViewModel.setEvent(MainContract.Event.Login) }) {
                Text("Navigate to Login")
            }
        }
    }
}