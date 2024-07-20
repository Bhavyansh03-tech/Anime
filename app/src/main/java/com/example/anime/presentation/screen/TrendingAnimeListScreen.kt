package com.example.anime.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.anime.presentation.screen.component.AnimeCard
import com.example.anime.presentation.viewModel.TrendingAnimeViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TrendingAnimeListScreen(
    onAnimeClick: (String?, Int?) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: TrendingAnimeViewModel = hiltViewModel()
) {
    val animeList by viewModel.animeData.collectAsStateWithLifecycle()
    
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        AnimatedContent(
            targetState = animeList.isEmpty(),
            label = "..."
        ) { isEmpty ->
            if (isEmpty) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        top = 15.dp + innerPadding.calculateTopPadding(),
                        bottom = 15.dp + innerPadding.calculateBottomPadding()
                    )
                ) {
                    item {
                        Text(
                            text = "Trending Anime",
                            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    
                    items(animeList){ anime ->
                        AnimeCard(
                            anime = anime,
                            onClick = {
                                onAnimeClick(
                                    anime.attributes.posterImage.original,
                                    anime.id.toInt()
                                )
                            },
                            animatedVisibilityScope = animatedVisibilityScope,
                            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceDim)
                        )
                    }
                }
            }
        }
    }
}