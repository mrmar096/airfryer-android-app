package com.mrmar.airfryer

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrmar.airfryer.core.presentation.router.Router
import com.mrmar.airfryer.core.presentation.router.routes.BackRoute
import com.mrmar.airfryer.login.presentation.route.LoginRouteGraph
import com.mrmar.airfryer.navigation.routes.SplashRoute
import com.mrmar.airfryer.splash.AnimationSplashContent
import com.mrmar.airfryer.splash.SplashContent

@Composable
fun AirfyerRouterCompose(router: Router) {
    val navController = rememberNavController()
    router.observeNavigation { route ->
        when (route) {
            is BackRoute -> {
                navController.popBackStack()
            }
            else -> {
                navController.navigate(route.getUriData(), route.buildOptions())
            }
        }
    }
    NavHost(navController, startDestination = SplashRoute.getUriData()) {
        splashComposable()
        LoginRouteGraph.build(this)
    }
}

private fun NavGraphBuilder.splashComposable() = composable(SplashRoute.getUriData()) {
    val scaleAnimation: Animatable<Float, AnimationVector1D> =
        remember { Animatable(initialValue = 0f) }

    AnimationSplashContent(
        scaleAnimation = scaleAnimation,
        durationMillisAnimation = 1500,
    )

    SplashContent(
        scaleAnimation = scaleAnimation
    )
}
