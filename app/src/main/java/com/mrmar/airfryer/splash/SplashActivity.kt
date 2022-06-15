package com.mrmar.airfryer.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.mrmar.airfryer.AirfyerRouterCompose
import com.mrmar.airfryer.core.presentation.router.Router
import com.mrmar.airfryer.core.ui.theme.AirfryerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO create splash screen Android 12
        super.onCreate(savedInstanceState)
        setContent {
            AirfryerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AirfyerRouterCompose(router = router)
                }
            }
        }
    }
}
