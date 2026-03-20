package com.mycohbasilan.novaris.core.ui.theme

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * Multi-preview annotation that renders composables in both **light** and **dark** theme.
 *
 * Usage:
 * ```kotlin
 * @ThemePreviews
 * @Composable
 * fun MyComponentPreview() {
 *     NovarisTheme { MyComponent() }
 * }
 * ```
 */
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class ThemePreviews
