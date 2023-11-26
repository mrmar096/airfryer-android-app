package com.mrmar.airfryer.core.presentation.router.di

import com.mrmar.airfryer.core.presentation.router.Navigator
import com.mrmar.airfryer.core.presentation.router.Router
import com.mrmar.airfryer.core.presentation.router.routes.BackRoute
import com.mrmar.airfryer.core.presentation.router.routes.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

object FakeRouter: Router {
    private val routerScope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)

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