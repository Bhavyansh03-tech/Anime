package com.example.anime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.anime.ui.theme.AnimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Installing Splash Screen :->
        installSplashScreen(
            // TODO after designing onboarding Screen

        )

        enableEdgeToEdge()
        setContent {
            AnimeTheme {

            }
        }
    }
}