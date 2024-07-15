package com.example.anime.presentation.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.anime.common.CircularProgressBar
import com.example.anime.common.IconButton
import com.example.anime.common.SkipTextButton
import com.example.anime.presentation.onboarding.components.OnBoardingPage
import com.example.anime.presentation.onboarding.dataClass.pages
import com.example.anime.presentation.onboarding.viewModel.OnBoardingEvents
import com.example.anime.util.Dimens.MediumPadding2
import com.example.anime.util.Dimens.ProgressBarSize
import com.example.anime.util.Dimens.ProgressStrokeWidth
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    event: (OnBoardingEvents) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Creating Pager State :->
        val pagerState = rememberPagerState(initialPage = 0){
            pages.size
        }

        // Creating button state for different states :->
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> Pair(Icons.AutoMirrored.Filled.ArrowForward, "skip")
                    1 -> Pair(Icons.AutoMirrored.Filled.ArrowForward, "skip")
                    2 -> Pair(Icons.Filled.Check, "")
                    else -> Pair("", "")
                }
            }
        }

        // Setup Onboarding Pages or Calling Pager :->
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState
        ) { index ->
            OnBoardingPage(page = pages[index])
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.2f))

        // Creating Page Indicator and button :->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2),
            contentAlignment = Alignment.Center
        ) {

            // Page Indicator :->
            CircularProgressBar(
                modifier = Modifier.size(ProgressBarSize),
                progress = (pagerState.currentPage + 1) / pages.size.toFloat(),
                color = MaterialTheme.colorScheme.primary,
                stroke = ProgressStrokeWidth
            )

            // Next Button :->
            val scope = rememberCoroutineScope()
            IconButton(
                icon = buttonState.value.first as ImageVector,
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            event(OnBoardingEvents.SaveAppEntry)
                        } else {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                }
            )


        }

        // Spacer :->
        Spacer(modifier = Modifier.height(4.dp))

        // Skip button
        val scope = rememberCoroutineScope()
        if (buttonState.value.second.isNotEmpty()) {
            SkipTextButton(
                text = "skip",
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(page = 2)
                    }
                }
            )
        }
    }

}