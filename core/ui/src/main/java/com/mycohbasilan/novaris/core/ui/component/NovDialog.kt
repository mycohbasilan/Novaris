package com.mycohbasilan.novaris.core.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Standard confirmation / information dialog following the Novaris design system.
 *
 * @param title Dialog title text.
 * @param text Dialog body / description text.
 * @param confirmText Label for the confirm button (default **"OK"**).
 * @param dismissText Label for the dismiss button. Pass `null` to hide it.
 * @param onConfirm Callback when the confirm button is tapped.
 * @param onDismiss Callback when the dialog is dismissed (back press, outside tap, dismiss button).
 * @param modifier Modifier applied to the [AlertDialog].
 */
@Composable
fun NovDialog(
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "OK",
    dismissText: String? = "Cancel"
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(confirmText)
            }
        },
        dismissButton = dismissText?.let {
            {
                TextButton(onClick = onDismiss) {
                    Text(it)
                }
            }
        },
        modifier = modifier
    )
}
