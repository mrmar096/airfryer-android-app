package com.mrmar.airfryer.core.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.mrmar.airfryer.core.ui.theme.Overlay

@Composable
fun LoadingScreen(
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        Surface(modifier = Modifier.disableGestures(true)) {
            Surface(
                color = Overlay,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            content()
        }
    } else {
        content()
    }
}