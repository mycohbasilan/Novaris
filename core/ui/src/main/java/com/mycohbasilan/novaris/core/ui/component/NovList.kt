package com.mycohbasilan.novaris.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.mycohbasilan.novaris.core.ui.theme.NovarisTheme
import com.mycohbasilan.novaris.core.ui.theme.ThemePreviews

// ──────────────────────────────────────────────
// Lazy List
// ──────────────────────────────────────────────

/**
 * Reusable [LazyColumn] wrapper that enforces consistent content padding and item spacing
 * from the Novaris design system.
 *
 * @param modifier Modifier applied to the outer [LazyColumn].
 * @param state Optional [LazyListState] for scroll control / observation.
 * @param contentPadding Padding around the entire list content.
 * @param verticalSpacing Vertical gap between items.
 * @param content Standard [LazyListScope] builder.
 */
@Composable
fun NovLazyList(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(NovarisTheme.spacing.md),
    verticalSpacing: Dp = NovarisTheme.spacing.sm,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = state,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        content = content
    )
}

// ──────────────────────────────────────────────
// Empty State
// ──────────────────────────────────────────────

/**
 * Centered empty-state placeholder shown when a list has no items.
 */
@Composable
fun NovEmptyState(message: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// ──────────────────────────────────────────────
// List Divider
// ──────────────────────────────────────────────

/**
 * Thin horizontal divider consistent with the design-system outline color.
 */
@Composable
fun NovListDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}

// ──────────────────────────────────────────────
// Previews
// ──────────────────────────────────────────────

@ThemePreviews
@Composable
private fun NovLazyListPreview() {
    NovarisTheme(dynamicColor = false) {
        NovLazyList {
            items(count = 5) { index ->
                Text(text = "Item #$index")
            }
        }
    }
}

@ThemePreviews
@Composable
private fun NovEmptyStatePreview() {
    NovarisTheme(dynamicColor = false) {
        NovEmptyState(message = "No items found")
    }
}
