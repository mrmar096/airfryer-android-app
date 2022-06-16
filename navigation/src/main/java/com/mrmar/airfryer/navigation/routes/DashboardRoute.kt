package com.mrmar.airfryer.navigation.routes

import androidx.navigation.NavOptions
import com.mrmar.airfryer.core.presentation.router.routes.Route

class DashboardRoute : Route {
    companion object {
        const val URI = "dashboard"
    }

    override fun getUriData(): String {
        return URI
    }

    override fun buildOptions(): NavOptions {
        return NavOptions.Builder().apply {
            setPopUpTo(LoginRoute.URI, inclusive = true)
        }.build()
    }
}