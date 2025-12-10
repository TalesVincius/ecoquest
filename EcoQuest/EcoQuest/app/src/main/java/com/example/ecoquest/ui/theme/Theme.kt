// com/example/ecoquest/ui/theme/Theme.kt
package com.example.ecoquest.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

// ⚠️ IMPORTANTE: NÃO importar `androidx.compose.material3.Shapes` aqui

// Paleta escura
private val DarkColorScheme = darkColorScheme(
    primary = EcoGreenSecondary,
    onPrimary = EcoBackgroundDark,
    primaryContainer = EcoGreenDark,
    onPrimaryContainer = EcoSurfaceLight,
    secondary = EcoAccentBlue,
    onSecondary = EcoBackgroundDark,
    background = EcoBackgroundDark,
    onBackground = EcoSurfaceLight,
    surface = EcoSurfaceDark,
    onSurface = EcoSurfaceLight,
    surfaceVariant = EcoGreenDark,
    onSurfaceVariant = EcoSurfaceLight,
    error = EcoErrorRed
)

// Paleta clara
private val LightColorScheme = lightColorScheme(
    primary = EcoGreenPrimary,
    onPrimary = EcoSurfaceLight,
    primaryContainer = EcoGreenSecondary,
    onPrimaryContainer = EcoSurfaceLight,
    secondary = EcoAccentBlue,
    onSecondary = EcoSurfaceLight,
    background = EcoBackgroundLight,
    onBackground = Color(0xFF1B1B1B),
    surface = EcoSurfaceLight,
    onSurface = Color(0xFF1B1B1B),
    surfaceVariant = EcoChipGreen,
    onSurfaceVariant = Color(0xFF1B1B1B),
    error = EcoErrorRed
)

@Composable
fun EcoQuestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Aqui usamos o `Shapes` que vem do arquivo Shapes.kt do seu próprio tema
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,   // <- NÃO é a classe, é o val definido em Shapes.kt
        content = content
    )
}
