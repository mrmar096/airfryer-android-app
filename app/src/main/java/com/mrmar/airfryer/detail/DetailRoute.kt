package com.mrmar.airfryer.detail

import androidx.navigation.*
import com.mrmar.airfryer.core.presentation.navigator.Route
import com.mrmar.airfryer.main.MainRoute

class DetailRoute(private val params: String) : Route<String> {
    companion object {
        private const val TEXT_PARAM = "text"
        const val URI = "detail/{text}"

        fun getArguments(): List<NamedNavArgument> = listOf(
            navArgument(TEXT_PARAM) { type = NavType.StringType }
        )

        fun resolveArguments(stackEntry: NavBackStackEntry): String {
            return stackEntry.arguments?.getString(TEXT_PARAM).orEmpty()
        }
    }

    override fun getFilledUri(): String {
        return "detail/$params"
    }

    override fun buildOptions(): NavOptions {
        return NavOptions.Builder().apply {
            setPopUpTo(MainRoute.getFilledUri(), false)
        }.build()
    }

}