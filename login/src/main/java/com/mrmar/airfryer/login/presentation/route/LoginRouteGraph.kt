package com.mrmar.airfryer.login.presentation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mrmar.airfryer.core.presentation.router.RouterGraph
import com.mrmar.airfryer.login.presentation.LoginScreen
import com.mrmar.airfryer.navigation.routes.LoginRoute

object LoginRouteGraph : RouterGraph {

    private const val LOGIN = "login"

    override fun build(graphBuilder: NavGraphBuilder) {
        graphBuilder.navigation(startDestination = LoginRoute.URI, LOGIN) {
            composable(
                LoginRoute.URI,
                LoginRoute.getArguments()
            ) {
                LoginScreen()
            }
        }
    }

}