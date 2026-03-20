# core:ui

Shared **Jetpack Compose** design-system module for the Novaris project.

## What's Inside

### Design System Tokens (`theme/`)

| File | Purpose |
|---|---|
| `Color.kt` | Full Material 3 light / dark color palette |
| `Type.kt` | Complete M3 typography scale (display → label) |
| `Spacing.kt` | Spacing tokens (`xxs` 2 dp → `xxl` 48 dp) via `NovarisTheme.spacing` |
| `Shape.kt` | M3 `Shapes` + extended shape tokens via `NovarisTheme.shapes` |
| `Theme.kt` | `NovarisTheme` composable — wraps `MaterialTheme` with all tokens |
| `ThemePreviews.kt` | `@ThemePreviews` multi-preview annotation (light + dark) |

### Reusable Components (`component/`)

| Component | Variants |
|---|---|
| `NovButton` | `NovPrimaryButton`, `NovSecondaryButton`, `NovOutlinedButton`, `NovTextButton`, `NovIconButton` |
| `NovTextField` | `NovTextField`, `NovPasswordField`, `NovSearchField` |
| `NovCard` | `NovCard` (filled), `NovOutlinedCard` |
| `NovList` | `NovLazyList`, `NovEmptyState`, `NovListDivider` |
| `NovTopAppBar` | Center-aligned top app bar with nav icon + actions |
| `NovDialog` | Confirm / dismiss alert dialog |
| `NovLoadingIndicator` | Centered spinner with optional message |

## Usage

### 1. Add the dependency

```kotlin
// feature-module/build.gradle.kts
dependencies {
    implementation(project(":core:ui"))
}
```

Compose BOM, Material 3, and UI libraries are exposed via `api`, so you do **not**
need to declare them again in the consuming module.

### 2. Apply the theme

```kotlin
NovarisTheme {
    // your composable content
}
```

### 3. Use design tokens

```kotlin
val padding = NovarisTheme.spacing.md   // 16.dp
val shape   = NovarisTheme.shapes.full  // 50 % rounded
```

### 4. Use components

```kotlin
NovPrimaryButton(text = "Submit", onClick = { /* … */ })
NovTextField(value = text, onValueChange = { text = it }, label = "Email")
NovLoadingIndicator(message = "Loading…")
```

### 5. Multi-preview

```kotlin
@ThemePreviews
@Composable
fun MyPreview() {
    NovarisTheme { MyComposable() }
}
```

