# Anime App

This is an Android application that fetches and displays trending anime information using the Kitsu API. The app includes a splash screen, an onboarding screen, and uses the MVVM architecture. It also incorporates shared elements for smooth transitions between screens.

## Features

- Fetches trending anime information from the Kitsu API
- Splash screen
- Onboarding screen
- MVVM architecture
- Shared element transitions

## Libraries and Tools

- Jetpack Compose
- Kitsu API
- Hilt for Dependency Injection
- Coil for image loading
- kotlinx.serialization for navigation

## Screenshots

*Include some screenshots of your app here*

## Getting Started

### Prerequisites

- Android Studio
- Kotlin

### Installation

1. Clone the repository:

   ```sh
     git clone https://github.com/Bhavyansh03-tech/Anime_App.git
   ```
   
2. Open the project in Android Studio.
3. Build the project and run it on an emulator or a physical device.

## Code Overview

### ViewModel

`AnimeViewModel` is responsible for fetching anime details and holding the data for `AnimeScreen`.

```kotlin
@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val api: KitsuRepository
) : ViewModel() {

    private var _anime = MutableStateFlow<AnimeData?>(null)
    val anime = _anime.asStateFlow()

    fun fetchAnime(id: Int) {
        viewModelScope.launch {
            _anime.update { api.getAnime(id) }
        }
    }
}
```

`TrendingAnimeViewModel` is responsible for fetching anime details and holding the data for `TrendingAnimeListScreen`.

```kotlin
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
```

`NavGraph SetUp`

```koltin
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
```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a new Pull Request.

## Contact

For questions or feedback, please contact [@Bhavyansh03-tech](https://github.com/Bhavyansh03-tech).

---
