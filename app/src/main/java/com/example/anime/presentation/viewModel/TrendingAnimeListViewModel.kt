package com.example.anime.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.domian.model.AnimeData
import com.example.anime.domian.repository.KitsuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingAnimeViewModel @Inject constructor(
    private val repository: KitsuRepository
) : ViewModel() {

    private var _animeData = MutableStateFlow<List<AnimeData>>(emptyList())
    val animeData = _animeData.asStateFlow()

    init {
        viewModelScope.launch {
            _animeData.update { repository.getTrendingAnime() }
        }
    }
}