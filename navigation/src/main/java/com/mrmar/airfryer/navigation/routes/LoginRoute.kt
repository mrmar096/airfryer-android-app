package com.mrmar.airfryer.navigation.routes

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mrmar.airfryer.core.presentation.router.routes.Route

class LoginRoute : Route {
    companion object {
        const val URI = "login"
    }

    override fun getUriData(): String {
        return "login"
    }

    override fun buildOptions(): NavOptions {
        return NavOptions.Builder().apply {
            setPopUpTo(SplashRoute.URI, inclusive = true)
        }.build()
    }
}

class LoginRouteError(private val fromError: String) : Route {
    companion object {
        private const val ERROR_PARAM = "error"

        const val URI = "login/{$ERROR_PARAM}"

        fun getArguments(): List<NamedNavArgument> = listOf(
            navArgument(ERROR_PARAM) { type = NavType.StringType }
        )

        fun resolveArguments(stateHandle: SavedStateHandle): String? {
            return stateHandle.get<String>(ERROR_PARAM)
        }
    }

    override fun getUriData(): String {
        return "login/$fromError"
    }

    override fun buildOptions(): NavOptions {
        return NavOptions.Builder().apply {
            setPopUpTo(SplashRoute.URI, inclusive = true)
        }.build()
    }

}