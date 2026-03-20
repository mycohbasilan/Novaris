package com.mycohbasilan.novaris.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

// ──────────────────────────────────────────────
// Material 3 Color Schemes
// ──────────────────────────────────────────────

private val LightColorScheme = lightColorScheme(
    primary = NovPrimaryLight,
    onPrimary = NovOnPrimaryLight,
    primaryContainer = NovPrimaryContainerLight,
    onPrimaryContainer = NovOnPrimaryContainerLight,
    secondary = NovSecondaryLight,
    onSecondary = NovOnSecondaryLight,
    secondaryContainer = NovSecondaryContainerLight,
    onSecondaryContainer = NovOnSecondaryContainerLight,
    tertiary = NovTertiaryLight,
    onTertiary = NovOnTertiaryLight,
    tertiaryContainer = NovTertiaryContainerLight,
    onTertiaryContainer = NovOnTertiaryContainerLight,
    error = NovErrorLight,
    onError = NovOnErrorLight,
    errorContainer = NovErrorContainerLight,
    onErrorContainer = NovOnErrorContainerLight,
    background = NovBackgroundLight,
    onBackground = NovOnBackgroundLight,
    surface = NovSurfaceLight,
    onSurface = NovOnSurfaceLight,
    surfaceVariant = NovSurfaceVariantLight,
    onSurfaceVariant = NovOnSurfaceVariantLight,
    outline = NovOutlineLight,
    outlineVariant = NovOutlineVariantLight,
    inverseSurface = NovInverseSurfaceLight,
    inverseOnSurface = NovInverseOnSurfaceLight,
    inversePrimary = NovInversePrimaryLight,
    scrim = NovScrim
)

private val DarkColorScheme = darkColorScheme(
    primary = NovPrimaryDark,
    onPrimary = NovOnPrimaryDark,
    primaryContainer = NovPrimaryContainerDark,
    onPrimaryContainer = NovOnPrimaryContainerDark,
    secondary = NovSecondaryDark,
    onSecondary = NovOnSecondaryDark,
    secondaryContainer = NovSecondaryContainerDark,
    onSecondaryContainer = NovOnSecondaryContainerDark,
    tertiary = NovTertiaryDark,
    onTertiary = NovOnTertiaryDark,
    tertiaryContainer = NovTertiaryContainerDark,
    onTertiaryContainer = NovOnTertiaryContainerDark,
    error = NovErrorDark,
    onError = NovOnErrorDark,
    errorContainer = NovErrorContainerDark,
    onErrorContainer = NovOnErrorContainerDark,
    background = NovBackgroundDark,
    onBackground = NovOnBackgroundDark,
    surface = NovSurfaceDark,
    onSurface = NovOnSurfaceDark,
    surfaceVariant = NovSurfaceVariantDark,
    onSurfaceVariant = NovOnSurfaceVariantDark,
    outline = NovOutlineDark,
    outlineVariant = NovOutlineVariantDark,
    inverseSurface = NovInverseSurfaceDark,
    inverseOnSurface = NovInverseOnSurfaceDark,
    inversePrimary = NovInversePrimaryDark,
    scrim = NovScrim
)

// ──────────────────────────────────────────────
// Theme Composable
// ──────────────────────────────────────────────

/**
 * Novaris application theme.
 *
 * Wraps [MaterialTheme] with the project's color, typography, and shape tokens
 * and provides extra design-system values via [CompositionLocalProvider]:
 * - `NovarisTheme.spacing` → [NovarisSpacing]
 * - `NovarisTheme.shapes`  → [NovarisExtendedShapes]
 *
 * @param darkTheme Whether to apply the dark color scheme. Defaults to system setting.
 * @param dynamicColor Whether to use Material You dynamic colors (Android 12+). Defaults to `true`.
 * @param content The composable content to theme.
 */
@Composable
fun NovarisTheme(
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

    CompositionLocalProvider(
        LocalNovarisSpacing provides NovarisSpacing(),
        LocalNovarisShapes provides NovarisExtendedShapes()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = NovarisTypography,
            shapes = NovarisShapes,
            content = content
        )
    }
}

// ──────────────────────────────────────────────
// Theme Object — convenience accessors
// ──────────────────────────────────────────────

/**
 * Convenience accessor for Novaris design-system tokens that live outside of [MaterialTheme].
 *
 * ```kotlin
 * val padding = NovarisTheme.spacing.md
 * val shape   = NovarisTheme.shapes.full
 * ```
 */
object NovarisTheme {
    /** Current [NovarisSpacing] provided by [NovarisTheme]. */
    val spacing: NovarisSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalNovarisSpacing.current

    /** Current [NovarisExtendedShapes] provided by [NovarisTheme]. */
    val shapes: NovarisExtendedShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalNovarisShapes.current
}
