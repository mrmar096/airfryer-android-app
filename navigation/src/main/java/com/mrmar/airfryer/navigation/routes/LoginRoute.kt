package com.mrmar.airfryer.navigation.routes

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mrmar.airfryer.core.presentation.router.routes.Route

class LoginRoute(private val params: String) : Route {
    companion object {
        private const val TEXT_PARAM = "text"
        const val URI = "login/{text}"

        fun getArguments(): List<NamedNavArgument> = listOf(
            navArgument(TEXT_PARAM) { type = NavType.StringType }
        )

        fun resolveArguments(stateHandle: SavedStateHandle): String {
            return (stateHandle.get<String>(TEXT_PARAM)
                ?: throw IllegalStateException("No text was passed to destination."))
        }
    }

    override fun getUriData(): String {
        return "login/$params"
    }

    override fun buildOptions(): NavOptions {
        return NavOptions.Builder().apply {
            setPopUpTo(MainRoute.getUriData(), false)
        }.build()
    }

}