package com.mrmar.airfryer.core.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun TopSnackbar(message: String, color: Color = Color.Red, duration: Long = 5000) {
    val (timer, setTimer) = remember { mutableStateOf(duration) }
    val second = 1000L
    LaunchedEffect(timer) {
        delay(second)
        if (timer != 0L) setTimer(timer - second)
    }
    AnimatedVisibility(
        visible = timer != 0L,
    ) {
        TopSnackbar(message = message, color = color)
    }
}

@Composable
private fun TopSnackbar(message: String, color: Color) {
    Column(modifier = Modifier.padding(PaddingValues(12.dp, 10.dp))) {
        Snackbar(
            modifier = Modifier.alignTop(), backgroundColor = color
        ) { Text(text = message) }
    }
}

@Composable
@Preview
fun PreviewTopSnackbar() {
    TopSnackbar(message = "Test Snack bar message!")
}