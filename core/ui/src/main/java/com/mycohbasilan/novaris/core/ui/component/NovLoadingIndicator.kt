package com.mycohbasilan.novaris.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mycohbasilan.novaris.core.ui.theme.NovarisTheme
import com.mycohbasilan.novaris.core.ui.theme.ThemePreviews

private val IndicatorSize = 48.dp

/**
 * Centered loading indicator with an optional descriptive message.
 *
 * @param modifier Modifier applied to the outer container.
 * @param message Optional text displayed below the spinner.
 */
@Composable
fun NovLoadingIndicator(modifier: Modifier = Modifier, message: String? = null) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(IndicatorSize),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 4.dp
        )
        if (message != null) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
            )
        }
    }
}

// ──────────────────────────────────────────────
// Previews
// ──────────────────────────────────────────────

@ThemePreviews
@Composable
private fun NovLoadingIndicatorPreview() {
    NovarisTheme(dynamicColor = false) {
        NovLoadingIndicator(message = "Loading…")
    }
}

@ThemePreviews
@Composable
private fun NovLoadingIndicatorNoMessagePreview() {
    NovarisTheme(dynamicColor = false) {
        NovLoadingIndicator()
    }
}
