package com.mrmar.airfryer.navigation.routes

import androidx.navigation.NavOptions
import com.mrmar.airfryer.core.presentation.router.routes.Route

object MainRoute : Route {

    private const val URI = "main"

    override fun getUriData(): String {
        return URI
    }

    override fun buildOptions(): NavOptions {
        return NavOptions.Builder().apply {
            setLaunchSingleTop(true)
        }.build()
    }

}