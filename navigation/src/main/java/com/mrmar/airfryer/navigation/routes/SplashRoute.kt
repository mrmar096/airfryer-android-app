package com.mrmar.airfryer.navigation.routes

import androidx.navigation.NavOptions
import com.mrmar.airfryer.core.presentation.router.routes.Route

object SplashRoute : Route {

    const val URI = "splash"

    override fun getUriData(): String {
        return URI
    }

    override fun buildOptions(): NavOptions {
        return NavOptions.Builder().build()
    }

}