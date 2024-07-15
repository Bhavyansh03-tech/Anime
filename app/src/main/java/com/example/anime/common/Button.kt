package com.example.anime.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.anime.util.Dimens.CIRCLE_BUTTON_PADDING
import com.example.anime.util.Dimens.CIRCLE_BUTTON_SIZE
import com.example.anime.util.Dimens.ICON_SIZE

@Composable
fun IconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = Modifier.size(CIRCLE_BUTTON_SIZE),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ), contentPadding = PaddingValues(CIRCLE_BUTTON_PADDING)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Next",
            modifier = Modifier.size(ICON_SIZE)
        )
    }

}

@Composable
fun SkipTextButton(
    text: String,
    onClick: () -> Unit
) {

    TextButton(
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onSurface
        )
    }

}