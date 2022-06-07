package com.mrmar.airfryer.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mrmar.airfryer.detail.DetailRoute
import com.mrmar.airfryer.ui.theme.AirfryerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainScreen(name: String) {
    AirfryerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "Hello $name!")
                Spacer(modifier = Modifier.height(14.dp))
                Button(onClick = {  }) {
                    Text("Navigate to Detail")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainContent() {
    val navController = rememberNavController()
    val router = MainRouter(navController)
    NavHost(
        navController,
        MainRoute.getFilledUri(),
        builder = router::buildRouterGraph
    )
    router.navigate(DetailRoute("Hola Mario"))
}
