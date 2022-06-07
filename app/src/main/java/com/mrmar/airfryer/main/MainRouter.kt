package com.mrmar.airfryer.main

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mrmar.airfryer.detail.DetailRoute
import com.mrmar.airfryer.detail.DetailScreen
import com.mrmar.airfryer.core.presentation.navigator.Router

class MainRouter(navController: NavController) : Router(navController) {

    override fun buildRouterGraph(graph: NavGraphBuilder) {
        graph.apply {
            composable(MainRoute.getFilledUri()) {
                MainScreen("Pepe")
            }
            composable(
                DetailRoute.URI,
                DetailRoute.getArguments()
            ) {
                DetailScreen(DetailRoute.resolveArguments(it))
            }
        }
    }

}