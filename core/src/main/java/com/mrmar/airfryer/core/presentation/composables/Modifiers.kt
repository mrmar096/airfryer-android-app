package com.mrmar.airfryer.core.presentation.composables

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout


fun Modifier.alignTop() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val height = placeable.height
    layout(placeable.width, height) {
        placeable.place(0, 0)
    }
}