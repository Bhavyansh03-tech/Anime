package com.example.anime.presentation.navigation.navGraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.anime.presentation.navigation.screenNames.Screens

fun NavGraphBuilder.homeNavGraph() {
    navigation<Screens.HomeNavigator>(
        startDestination = Screens.HomeScreen
    ){
        composable<Screens.HomeScreen>{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Home Screen",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}