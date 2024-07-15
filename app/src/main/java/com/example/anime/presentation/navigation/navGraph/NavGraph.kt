package com.example.anime.presentation.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.anime.presentation.navigation.screenNames.Screens

@Composable
fun NavGraph(
    startDestination: Screens
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        onboardingNavGraph()
        homeNavGraph()
    }
}