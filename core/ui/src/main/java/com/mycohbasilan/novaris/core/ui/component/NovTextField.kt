package com.mycohbasilan.novaris.core.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.mycohbasilan.novaris.core.ui.theme.NovarisTheme
import com.mycohbasilan.novaris.core.ui.theme.ThemePreviews

// ──────────────────────────────────────────────
// Standard Text Field
// ──────────────────────────────────────────────

/**
 * Standard outlined text field with optional label, placeholder, leading/trailing icons,
 * and error state.
 */
@Composable
fun NovTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = label?.let { { Text(it) } },
        placeholder = placeholder?.let { { Text(it) } },
        leadingIcon = leadingIcon?.let {
            { Icon(imageVector = it, contentDescription = null) }
        },
        trailingIcon = trailingIcon?.let { icon ->
            {
                IconButton(onClick = { onTrailingIconClick?.invoke() }) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        },
        isError = isError,
        supportingText = if (isError && errorMessage != null) {
            {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        } else {
            null
        },
        enabled = enabled,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

// ──────────────────────────────────────────────
// Password Field
// ──────────────────────────────────────────────

/**
 * Outlined password field with built-in visibility toggle.
 *
 * @param visibleIcon Icon shown when the password is **visible** (tap to hide).
 * @param hiddenIcon Icon shown when the password is **hidden** (tap to reveal).
 */
@Composable
fun NovPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    visibleIcon: ImageVector,
    hiddenIcon: ImageVector,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = label?.let { { Text(it) } },
        placeholder = placeholder?.let { { Text(it) } },
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            val icon = if (passwordVisible) visibleIcon else hiddenIcon
            val description = if (passwordVisible) "Hide password" else "Show password"
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = icon, contentDescription = description)
            }
        },
        isError = isError,
        supportingText = if (isError && errorMessage != null) {
            {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        } else {
            null
        },
        enabled = enabled,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions
    )
}

// ──────────────────────────────────────────────
// Search Field
// ──────────────────────────────────────────────

/**
 * Outlined search field with a leading search icon and a trailing clear button
 * that appears when the query is non-empty.
 *
 * @param searchIcon Icon displayed at the start of the field.
 * @param clearIcon Icon displayed at the end to clear the query.
 */
@Composable
fun NovSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    searchIcon: ImageVector,
    clearIcon: ImageVector,
    modifier: Modifier = Modifier,
    placeholder: String = "Search…",
    onSearch: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(imageVector = searchIcon, contentDescription = "Search")
        },
        trailingIcon = if (query.isNotEmpty()) {
            {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(imageVector = clearIcon, contentDescription = "Clear")
                }
            }
        } else {
            null
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch?.invoke() })
    )
}

// ──────────────────────────────────────────────
// Previews
// ──────────────────────────────────────────────

@ThemePreviews
@Composable
private fun NovTextFieldPreview() {
    NovarisTheme(dynamicColor = false) {
        NovTextField(
            value = "",
            onValueChange = {},
            label = "Email",
            placeholder = "you@example.com"
        )
    }
}

@ThemePreviews
@Composable
private fun NovTextFieldErrorPreview() {
    NovarisTheme(dynamicColor = false) {
        NovTextField(
            value = "bad",
            onValueChange = {},
            label = "Email",
            isError = true,
            errorMessage = "Invalid email address"
        )
    }
}
