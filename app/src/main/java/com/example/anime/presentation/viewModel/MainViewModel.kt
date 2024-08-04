package com.example.anime.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.domian.useCases.AppEntryUseCases
import com.example.anime.presentation.navigation.screenNames.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private val _splashCondition = MutableStateFlow(true)
    val splashCondition: StateFlow<Boolean> = _splashCondition

    private val _startDestination = MutableStateFlow<Screens?>(null)
    val startDestination: StateFlow<Screens?> = _startDestination

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
                _startDestination.value = if (shouldStartFromHomeScreen) Screens.HomeNavigator else Screens.AppStartNavigation
                Log.d("MainViewModel", "Start destination: ${_startDestination.value}")
                delay(100)
                _splashCondition.value = false
            }.launchIn(this)
        }
    }
}