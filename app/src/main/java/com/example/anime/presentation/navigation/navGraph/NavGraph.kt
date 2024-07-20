package com.example.anime.presentation.navigation.navGraph

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.anime.presentation.navigation.screenNames.Screens
import com.example.anime.presentation.onboarding.OnboardingScreen
import com.example.anime.presentation.onboarding.viewModel.OnboardingViewModel
import com.example.anime.presentation.screen.TrendingAnimeListScreen
import com.example.anime.presentation.screen.AnimeScreen
// 2, 3, 15:30, 16.
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(
    startDestination: Screens
) {
    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            navigation<Screens.AppStartNavigation>(
                startDestination = Screens.OnboardingScreen
            ){
                composable<Screens.OnboardingScreen>{
                    // Initializing the view model :->
                    val viewModel = hiltViewModel<OnboardingViewModel>()

                    // Calling Onboarding Screen :->
                    OnboardingScreen(
                        event = viewModel::onEvent
                    )
                }
            }

            navigation<Screens.HomeNavigator>(
                startDestination = Screens.HomeScreen
            ) {
                composable<Screens.HomeScreen> {
//                    Box(modifier = Modifier.fillMaxSize()){
//                        Text(text = "Home World", style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold))
//                    }
                    TrendingAnimeListScreen(
                        onAnimeClick = { coverImage, id ->
                            navController.navigate(
                                Screens.AnimeScreen(id.toString(), coverImage.toString())
                            )
                        },
                        animatedVisibilityScope = this
                    )
                }

                composable<Screens.AnimeScreen> {
                    val args = it.toRoute<Screens.AnimeScreen>()

                    AnimeScreen(
                        id = args.id.toInt(),
                        coverImage = args.coverImage,
                        animatedVisibilityScope = this
                    )
                }
            }
        }
    }
}