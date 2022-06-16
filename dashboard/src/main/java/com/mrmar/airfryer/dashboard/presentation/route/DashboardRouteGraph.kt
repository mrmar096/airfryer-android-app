package com.mrmar.airfryer.dashboard.presentation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mrmar.airfryer.core.presentation.router.RouterGraph
import com.mrmar.airfryer.dashboard.presentation.ui.DashboardScreen
import com.mrmar.airfryer.navigation.routes.DashboardRoute

object DashboardRouteGraph : RouterGraph {

    private const val DASHBOARD = "dashboard_graph"

    override fun build(graphBuilder: NavGraphBuilder) {
        graphBuilder.navigation(startDestination = DashboardRoute.URI, DASHBOARD) {
            composable(
                DashboardRoute.URI,
            ) {
                DashboardScreen()
            }
        }
    }

}