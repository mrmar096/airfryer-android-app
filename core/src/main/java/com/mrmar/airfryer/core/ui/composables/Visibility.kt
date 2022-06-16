package com.mrmar.airfryer.core.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay

@Composable
fun TemporalContent(
    duration: Long = 5000,
    content: @Composable () -> Unit
) {
    val (timer, setTimer) = remember { mutableStateOf(duration) }
    val second = 1000L
    LaunchedEffect(timer) {
        delay(second)
        if (timer != 0L) setTimer(timer - second)
    }
    AnimatedVisibility(
        visible = timer != 0L,
    ) {
        content()
    }
}

@Composable
fun <T> visibility(condition: Boolean, content: @Composable () -> T): T? {
    return if (condition) content() else null
}