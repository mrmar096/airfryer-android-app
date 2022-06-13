package com.mrmar.airfryer.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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
import com.mrmar.airfryer.navigation.routes.LoginRoute
import com.mrmar.airfryer.navigation.routes.MainRoute

@Composable
fun MainScreen(mainRouter: Router) {
    AirfryerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val navController = rememberNavController()
            mainRouter.observeNavigation { route ->
                when (route) {
                    is BackRoute -> {
                        navController.popBackStack()
                    }
                    is LoginRoute -> {
                        navController.popBackStack()
                        navController.navigate(route.getUriData(), route.buildOptions())
                    }
                    else -> {
                        navController.navigate(route.getUriData(), route.buildOptions())
                    }
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


@Preview(showBackground = true)
@Composable
fun MainContent() {
    val viewModel = hiltViewModel<MainViewModel>()
}