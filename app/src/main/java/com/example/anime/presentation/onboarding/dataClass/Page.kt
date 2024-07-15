package com.example.anime.presentation.onboarding.dataClass

import androidx.annotation.DrawableRes
import com.example.anime.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

// Onboarding pages list :->
val pages = listOf(
    Page(
        title = "Explore Anime",
        description = "Dive into detailed stories and character backgrounds. Explore the rich world of your favorite anime.",
        image = R.drawable.img
    ),
    Page(
        title = "Deep Insights",
        description = "Get comprehensive information on various anime. Learn about plots, characters, and more.",
        image = R.drawable.img_1
    ),
    Page(
        title = "Stay Updated",
        description = "Keep up with the latest news and updates. Always be informed about new releases and trends.",
        image = R.drawable.img_2
    )
)
