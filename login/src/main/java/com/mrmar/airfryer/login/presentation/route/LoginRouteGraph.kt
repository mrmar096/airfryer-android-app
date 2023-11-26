package com.mrmar.airfryer.login.presentation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mrmar.airfryer.core.presentation.router.RouterGraph
import com.mrmar.airfryer.login.presentation.LoginScreen
import com.mrmar.airfryer.navigation.routes.LoginRoute
import com.mrmar.airfryer.navigation.routes.LoginRouteError

object LoginRouteGraph : RouterGraph {

    const val LOGIN = "login_graph"

    override fun build(graphBuilder: NavGraphBuilder) {
        graphBuilder.navigation(startDestination = LoginRoute.URI, LOGIN) {
            composable(
                LoginRoute.URI,
            ) {
                LoginScreen()
            }
            composable(
                LoginRouteError.URI,
                LoginRouteError.getArguments()
            ) {
                LoginScreen()
            }
        }
    }

}