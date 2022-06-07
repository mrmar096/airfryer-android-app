package com.mrmar.airfryer.main

import androidx.navigation.NavOptions
import com.mrmar.airfryer.core.presentation.navigator.Route

object MainRoute : Route<String> {

    override fun getFilledUri(): String {
        return "main"
    }

    override fun buildOptions(): NavOptions {
        return NavOptions.Builder().apply {
            setLaunchSingleTop(true)
        }.build()
    }

}