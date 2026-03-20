package com.mycohbasilan.novaris.core.ui.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mycohbasilan.novaris.core.ui.theme.NovarisTheme
import com.mycohbasilan.novaris.core.ui.theme.ThemePreviews

/**
 * Filled card variant. Uses the Material 3 filled card elevation/surface tint.
 */
@Composable
fun NovCard(modifier: Modifier = Modifier, onClick: (() -> Unit)? = null, content: @Composable ColumnScope.() -> Unit) {
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            content = content
        )
    } else {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            content = content
        )
    }
}

/**
 * Outlined card variant. Suited for items that need clear visual boundaries.
 */
@Composable
fun NovOutlinedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    if (onClick != null) {
        OutlinedCard(
            onClick = onClick,
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            content = content
        )
    } else {
        OutlinedCard(
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            content = content
        )
    }
}

// ──────────────────────────────────────────────
// Previews
// ──────────────────────────────────────────────

@ThemePreviews
@Composable
private fun NovCardPreview() {
    NovarisTheme(dynamicColor = false) {
        NovCard(modifier = Modifier.padding(NovarisTheme.spacing.md)) {
            Text(
                text = "Filled Card",
                modifier = Modifier.padding(NovarisTheme.spacing.md)
            )
        }
    }
}

@ThemePreviews
@Composable
private fun NovOutlinedCardPreview() {
    NovarisTheme(dynamicColor = false) {
        NovOutlinedCard(modifier = Modifier.padding(NovarisTheme.spacing.md)) {
            Text(
                text = "Outlined Card",
                modifier = Modifier.padding(NovarisTheme.spacing.md)
            )
        }
    }
}
