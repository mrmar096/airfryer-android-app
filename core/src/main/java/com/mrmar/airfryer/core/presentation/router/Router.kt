package com.mrmar.airfryer.core.presentation.router

import com.mrmar.airfryer.core.presentation.router.routes.Route

interface Router {
    fun navigate(route: Route)
    fun navigateBack()
    fun observeNavigation(navigate: (Route) -> Unit)
}