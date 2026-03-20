package com.mycohbasilan.novaris.core.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.mycohbasilan.novaris.core.ui.theme.NovarisTheme
import com.mycohbasilan.novaris.core.ui.theme.ThemePreviews

/**
 * Center-aligned top app bar following Material 3 guidelines.
 *
 * @param title Title text.
 * @param modifier Modifier applied to the bar.
 * @param navigationIcon Optional leading icon (e.g. back arrow).
 * @param onNavigationClick Callback when the navigation icon is tapped.
 * @param actions Optional trailing action icons.
 * @param scrollBehavior Optional scroll behavior for collapsing / scrolling effects.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (navigationIcon != null && onNavigationClick != null) {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = "Navigate back"
                    )
                }
            }
        },
        actions = { actions() },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        scrollBehavior = scrollBehavior
    )
}

// ──────────────────────────────────────────────
// Previews
// ──────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
private fun NovTopAppBarPreview() {
    NovarisTheme(dynamicColor = false) {
        NovTopAppBar(title = "Novaris")
    }
}
