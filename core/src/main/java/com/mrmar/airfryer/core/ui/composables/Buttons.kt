package com.mrmar.airfryer.core.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mrmar.airfryer.core.R

@Composable
fun ExpandableProgressFloatingActionButton(
    text: String,
    color: Color,
    isFolded: Boolean = false,
    isEnabled: Boolean = false,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { if(isEnabled) onClick() },
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        backgroundColor = color,
        contentColor = Color.White
    ) {
        AnimatedVisibility(visible = isFolded) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
                Icon(
                    painter = painterResource(id = R.drawable.ic_stop),
                    contentDescription = "Stop"
                )
            }
        }
        AnimatedVisibility(visible = !isFolded, modifier = Modifier.padding(horizontal= 12.dp)) {
            Text(text)
        }
    }
}
