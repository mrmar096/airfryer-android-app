package com.mrmar.airfryer.core.presentation.router

import com.mrmar.airfryer.core.presentation.router.routes.BackRoute
import com.mrmar.airfryer.core.presentation.router.routes.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirfyerRouter @Inject constructor() : Router {

    private val routerScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val navigator: Navigator = Navigator

    override fun navigate(route: Route) {
        navigator.navigate(route)
    }

    override fun navigateBack() {
        navigator.navigate(BackRoute)
    }

    override fun observeNavigation(navigate: (Route) -> Unit) {
        navigator.flow.onEach {
            navigate(it)
        }.launchIn(routerScope)
    }


}