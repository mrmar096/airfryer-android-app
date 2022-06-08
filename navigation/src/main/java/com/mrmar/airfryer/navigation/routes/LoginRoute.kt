package com.mrmar.airfryer.navigation.routes

import androidx.navigation.*
import com.mrmar.airfryer.core.presentation.router.routes.Route

class LoginRoute(private val params: String) : Route {
    companion object {
        private const val TEXT_PARAM = "text"
        const val URI = "login/{text}"

        fun getArguments(): List<NamedNavArgument> = listOf(
            navArgument(TEXT_PARAM) { type = NavType.StringType }
        )

        fun resolveArguments(stackEntry: NavBackStackEntry): String {
            return stackEntry.arguments?.getString(TEXT_PARAM).orEmpty()
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