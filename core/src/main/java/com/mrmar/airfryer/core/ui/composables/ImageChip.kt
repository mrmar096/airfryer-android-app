package com.mrmar.airfryer.core.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrmar.airfryer.core.ui.theme.Inactive
import com.mrmar.airfryer.core.ui.theme.Purple500

@Composable
fun ImageChip(
    text: String,
    icon: Painter,
    isEnabled: Boolean,
    isSelected: Boolean,
    onClick: (() -> Unit)? = null
) {
    val tintColor = if (!isEnabled) Inactive else if (isSelected) Color.White else Purple500
    Surface(
        modifier = Modifier
            .padding(all = 4.dp)
            .clickable(
                onClick = { if (isEnabled) onClick?.invoke() },
            ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Inactive
        ),
        color = if (isSelected) Purple500 else Color.Transparent
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Icon(
                icon,
                contentDescription = "...",
                tint = tintColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = tintColor
                )
            )
        }

    }
}