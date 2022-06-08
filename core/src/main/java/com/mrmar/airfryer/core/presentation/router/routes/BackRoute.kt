package com.mrmar.airfryer.core.presentation.router.routes

import androidx.navigation.NavOptions

object BackRoute : Route {

    private const val URI = "back"

    override fun getUriData(): String {
        return URI
    }

    override fun buildOptions(): NavOptions {
        return NavOptions.Builder().build()
    }

}