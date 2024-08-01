package com.example.anime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.anime.presentation.navigation.navGraph.NavGraph
import com.example.anime.presentation.viewModel.MainViewModel
import com.example.anime.ui.theme.AnimeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Splash Screen :->
        val splashScreen = installSplashScreen()

        // Use coroutine scope to handle the splash condition
        val splashScope = CoroutineScope(Dispatchers.Main)
        splashScope.launch {
            viewModel.splashCondition.collect { splashCondition ->
                splashScreen.setKeepOnScreenCondition { splashCondition }
            }
        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            AnimeTheme {
                Box(
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                ){
                    val startDestination by viewModel.startDestination.collectAsState()
                    if (startDestination != null) {
                        NavGraph(startDestination = startDestination!!)
                    }
                }
            }
        }
    }
}