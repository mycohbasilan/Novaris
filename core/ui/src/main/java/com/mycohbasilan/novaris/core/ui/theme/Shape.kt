package com.mycohbasilan.novaris.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Material 3 [Shapes] set for the Novaris design system.
 */
val NovarisShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

/**
 * Extended shape tokens beyond what Material 3 [Shapes] provides.
 *
 * Access via `NovarisTheme.shapes`.
 */
@Immutable
data class NovarisExtendedShapes(
    val none: Shape = RectangleShape,
    val xs: Shape = RoundedCornerShape(4.dp),
    val sm: Shape = RoundedCornerShape(8.dp),
    val md: Shape = RoundedCornerShape(12.dp),
    val lg: Shape = RoundedCornerShape(16.dp),
    val xl: Shape = RoundedCornerShape(28.dp),
    val full: Shape = RoundedCornerShape(percent = 50)
)

/**
 * CompositionLocal used to pass [NovarisExtendedShapes] down the composition tree.
 */
val LocalNovarisShapes = staticCompositionLocalOf { NovarisExtendedShapes() }
