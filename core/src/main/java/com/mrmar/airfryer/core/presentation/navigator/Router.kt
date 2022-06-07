package com.mrmar.airfryer.core.presentation.navigator

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

abstract class Router(private val navController: NavController) {

    abstract fun buildRouterGraph(graph: NavGraphBuilder)

    fun navigate(route: Route<*>) {
        navController.navigate(route.getFilledUri(), route.buildOptions())
    }
}