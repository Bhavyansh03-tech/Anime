package com.example.anime.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.anime.domian.useCases.AppEntryUseCases
import com.example.anime.presentation.navigation.screenNames.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf<Screens>(Screens.AppStartNavigation)
        private set

    init {
        CoroutineScope(Dispatchers.IO).launch {
            appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
                startDestination = if (shouldStartFromHomeScreen) Screens.HomeNavigator else Screens.AppStartNavigation
                delay(300)
                splashCondition = false
            }.launchIn(this)
        }
    }
}