package com.mrmar.airfryer.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrmar.airfryer.ui.theme.AirfryerTheme

@Composable
fun DetailScreen(params: String) {
    AirfryerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "Hello $params!")
                Spacer(modifier = Modifier.height(14.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text("Navigate back to Main")
                }
            }
        }
    }
}