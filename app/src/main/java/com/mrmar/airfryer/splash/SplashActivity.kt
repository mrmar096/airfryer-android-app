package com.mrmar.airfryer.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mrmar.airfryer.core.presentation.router.Router
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
            Splash(router)
        }
    }
}
