package com.mrmar.airfryer.core.presentation.router

import com.mrmar.airfryer.core.presentation.router.routes.Route
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object Navigator {

    private val routeFlow = MutableSharedFlow<Route>(extraBufferCapacity = 1)

    val flow = routeFlow.asSharedFlow()

    fun navigate(route: Route) {
        routeFlow.tryEmit(route)
    }
}