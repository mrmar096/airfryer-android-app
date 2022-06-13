package com.mrmar.airfryer.navigation.routes

import androidx.navigation.NavOptions
import com.mrmar.airfryer.core.presentation.router.routes.Route

class LoginRoute : Route {
    companion object {
        const val URI = "login"
    }

    override fun getUriData(): String {
        return "login"
    }

    override fun buildOptions(): NavOptions {
        return NavOptions.Builder().build()
    }

}