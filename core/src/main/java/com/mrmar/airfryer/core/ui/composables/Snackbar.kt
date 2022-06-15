package com.mrmar.airfryer.core.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TopSnackbar(message: String, color: Color = Color.Red, duration: Long = 5000) {
    TemporalContent(duration) {
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