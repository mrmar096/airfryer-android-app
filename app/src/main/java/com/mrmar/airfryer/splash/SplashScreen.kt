package com.mrmar.airfryer.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrmar.airfryer.R
import com.mrmar.airfryer.core.ui.theme.Purple500
import com.mrmar.airfryer.core.ui.theme.Purple700
import com.mrmar.airfryer.core.ui.theme.Teal200
import com.mrmar.airfryer.splash.viewmodel.SplashViewModel

@Composable
fun AnimationSplashContent(
    scaleAnimation: Animatable<Float, AnimationVector1D>,
    durationMillisAnimation: Int,
) {

    LaunchedEffect(key1 = true) {
        scaleAnimation.animateTo(
            targetValue = 0.5F,
            animationSpec = infiniteRepeatable(
                repeatMode = RepeatMode.Reverse,
                animation = tween(
                    durationMillis = durationMillisAnimation,
                    easing = {
                        OvershootInterpolator(3F).getInterpolation(it)
                    }
                )
            )
        )
    }
}

@Composable
fun SplashContent(
    scaleAnimation: Animatable<Float, AnimationVector1D>
) {
    hiltViewModel<SplashViewModel>()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Purple700,
                        Purple500,
                        Teal200,
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(
                    id = R.drawable.airfryer
                ),
                contentDescription = "Logo Splash Screen",
                modifier = Modifier
                    .size(400.dp)
                    .scale(scale = scaleAnimation.value),
            )
        }
    }
}