package com.example.anime.presentation.navigation.screenNames

import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable object HomeScreen
    @Serializable object OnboardingScreen
    @Serializable data object AppStartNavigation : Screens()
    @Serializable data object HomeNavigator : Screens()
    @Serializable data class AnimeScreen(val id:String, val coverImage: String)

}