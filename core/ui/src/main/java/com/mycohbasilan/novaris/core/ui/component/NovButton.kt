package com.mycohbasilan.novaris.core.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mycohbasilan.novaris.core.ui.theme.NovarisTheme
import com.mycohbasilan.novaris.core.ui.theme.ThemePreviews

// ──────────────────────────────────────────────
// Primary Button
// ──────────────────────────────────────────────

/**
 * High-emphasis filled button used for the primary action on a screen.
 */
@Composable
fun NovPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    ) {
        LeadingIconContent(leadingIcon)
        Text(text = text)
    }
}

// ──────────────────────────────────────────────
// Secondary (Tonal) Button
// ──────────────────────────────────────────────

/**
 * Medium-emphasis tonal button for secondary actions.
 */
@Composable
fun NovSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    ) {
        LeadingIconContent(leadingIcon)
        Text(text = text)
    }
}

// ──────────────────────────────────────────────
// Outlined Button
// ──────────────────────────────────────────────

/**
 * Medium-emphasis outlined button for less prominent actions.
 */
@Composable
fun NovOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    ) {
        LeadingIconContent(leadingIcon)
        Text(text = text)
    }
}

// ──────────────────────────────────────────────
// Text Button
// ──────────────────────────────────────────────

/**
 * Low-emphasis text button for the least prominent actions.
 */
@Composable
fun NovTextButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Text(text = text)
    }
}

// ──────────────────────────────────────────────
// Icon Button
// ──────────────────────────────────────────────

/**
 * Standard icon button for toolbar and auxiliary actions.
 */
@Composable
fun NovIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// ──────────────────────────────────────────────
// Internal helper
// ──────────────────────────────────────────────

@Composable
private fun LeadingIconContent(icon: ImageVector?) {
    if (icon != null) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
    }
}

// ──────────────────────────────────────────────
// Previews
// ──────────────────────────────────────────────

@ThemePreviews
@Composable
private fun NovPrimaryButtonPreview() {
    NovarisTheme(dynamicColor = false) {
        NovPrimaryButton(text = "Primary", onClick = {})
    }
}

@ThemePreviews
@Composable
private fun NovSecondaryButtonPreview() {
    NovarisTheme(dynamicColor = false) {
        NovSecondaryButton(text = "Secondary", onClick = {})
    }
}

@ThemePreviews
@Composable
private fun NovOutlinedButtonPreview() {
    NovarisTheme(dynamicColor = false) {
        NovOutlinedButton(text = "Outlined", onClick = {})
    }
}

@ThemePreviews
@Composable
private fun NovTextButtonPreview() {
    NovarisTheme(dynamicColor = false) {
        NovTextButton(text = "Text", onClick = {})
    }
}
