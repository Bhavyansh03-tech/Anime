package com.example.anime.presentation.onboarding.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.anime.R
import com.example.anime.common.ClippedImage
import com.example.anime.presentation.onboarding.dataClass.Page
import com.example.anime.util.Dimens.MediumPadding1
import com.example.anime.util.Dimens.MediumPadding2

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {

    Column(
        modifier = modifier
    ) {

        // Onboarding Page Image :->
        ClippedImage(
            shapeImageId = R.drawable.onboarding_design,
            contentImageId = page.image
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        // Onboarding Page Title :->
        Text(
            text = page.title,
            modifier = Modifier
                .padding(horizontal = MediumPadding2)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        // Onboarding Page Description :->
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }


}