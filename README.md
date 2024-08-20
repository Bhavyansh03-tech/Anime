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
### Dark Mode :
<div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/user-attachments/assets/3a59f7f4-39e0-4da4-8239-71aea389eb71" alt="First Screenshot" style="width: 200px; height: auto; margin-right: 10px;">
    <img src="https://github.com/user-attachments/assets/d08acb33-87b1-47f8-b271-a14de9e0392e" alt="Second Screenshot" style="width: 200px; height: auto;">
</div>

### Light Mode :
<div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/user-attachments/assets/f16a180a-2b9b-4c75-8913-7de66c2e469e" alt="First Screenshot" style="width: 200px; height: auto; margin-right: 10px;">
    <img src="https://github.com/user-attachments/assets/77c29526-e106-4104-a1f8-b84c888c69cb" alt="Second Screenshot" style="width: 200px; height: auto;">
</div>

## Getting Started

### Prerequisites

- Android Studio
- Kotlin

### Installation

1. Clone the repository:

   ```bash
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

For questions or feedback, please contact [@Bhavyansh03-tech](https://github.com/Bhavyansh03-tech) on GitHub or connect with me on [LinkedIn](https://www.linkedin.com/in/bhavyansh03/).

---
