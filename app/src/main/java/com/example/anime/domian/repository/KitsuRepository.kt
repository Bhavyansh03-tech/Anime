package com.example.anime.domian.repository

import com.example.anime.domian.model.AnimeData

interface KitsuRepository {

    suspend fun getTrendingAnime(): List<AnimeData>

    suspend fun getAnime(id: Int): AnimeData?
}