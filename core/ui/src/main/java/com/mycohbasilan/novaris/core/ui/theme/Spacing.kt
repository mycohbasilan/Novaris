package com.mycohbasilan.novaris.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacing tokens for the Novaris design system.
 *
 * Access via `NovarisTheme.spacing`, e.g. `NovarisTheme.spacing.md`.
 *
 * | Token | Value | Typical use |
 * |-------|-------|-------------|
 * | xxs   |  2 dp | Hairline gaps |
 * | xs    |  4 dp | Tight inner padding |
 * | sm    |  8 dp | Standard inner padding |
 * | md    | 16 dp | Section padding / default content padding |
 * | lg    | 24 dp | Card / dialog padding |
 * | xl    | 32 dp | Screen-level padding |
 * | xxl   | 48 dp | Large spacer / hero sections |
 */
@Immutable
data class NovarisSpacing(
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 24.dp,
    val xl: Dp = 32.dp,
    val xxl: Dp = 48.dp
)

/**
 * CompositionLocal used to pass [NovarisSpacing] down the composition tree.
 */
val LocalNovarisSpacing = staticCompositionLocalOf { NovarisSpacing() }
