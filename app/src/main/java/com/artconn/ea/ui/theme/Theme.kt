package com.artconn.ea.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ArtColors = lightColorScheme(
    primary = Color(0xFFB5482E),
    onPrimary = Color.White,
    secondary = Color(0xFF2F6F73),
    onSecondary = Color.White,
    background = Color(0xFFFFFBF7),
    onBackground = Color(0xFF1F1B16),
    surface = Color(0xFFFFFBF7),
    onSurface = Color(0xFF1F1B16),
    error = Color(0xFFB3261E)
)

@Composable
fun ArtConnTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = ArtColors, content = content)
}
