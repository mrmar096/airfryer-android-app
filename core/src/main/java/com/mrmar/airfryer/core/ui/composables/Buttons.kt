package com.mrmar.airfryer.core.ui.composables

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButton(text: String, onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = { Text(text) },
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    )
}
