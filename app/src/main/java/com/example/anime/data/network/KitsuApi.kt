package com.example.anime.data.network

import com.example.anime.data.dto.AnimeResponseDto
import com.example.anime.data.dto.TrendingAnimeListDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface KitsuApi {

    @GET("trending/anime")
    suspend fun getTrendingAnime(): ApiResponse<TrendingAnimeListDto>

    @GET("anime/{id}")
    suspend fun getAnime(
        @Path("id") id: Int
    ): ApiResponse<AnimeResponseDto?>
}