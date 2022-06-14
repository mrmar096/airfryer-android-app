package com.mrmar.airfryer.core.ui.composables

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout


fun Modifier.alignTop() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val height = placeable.height
    layout(placeable.width, height) {
        placeable.place(0, 0)
    }
}

fun Modifier.disableGestures(disabled: Boolean = true) =
    if (disabled) {
        pointerInput(Unit) {
            awaitPointerEventScope {
                // we should wait for all new pointer events
                while (true) {
                    awaitPointerEvent(pass = PointerEventPass.Initial)
                        .changes
                        .forEach(PointerInputChange::consume)
                }
            }
        }
    } else {
        Modifier
    }