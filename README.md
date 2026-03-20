# Novaris

An Android application built with modern Android development practices.

## Tech Stack

| Category | Library | Version |
|---|---|---|
| Language | Kotlin | 2.2.10 |
| UI | Jetpack Compose (BOM) | 2026.03.00 |
| DI | Dagger Hilt | 2.59.2 |
| Networking | Retrofit + OkHttp + Kotlin Serialization | 2.11.0 / 4.12.0 / 1.8.1 |
| Local Storage | Room | 2.7.1 |
| Code Processing | KSP | 2.2.10-2.0.2 |
| Code Quality | Detekt + ktlint | 1.23.8 / 14.2.0 |
| Build System | AGP + Gradle | 9.1.0 |

## Project Structure

```
Novaris/
├── app/                    # Application module — entry point, UI, navigation
├── core/
│   ├── network/            # :core:network — Retrofit, OkHttp, serialization
│   └── database/           # :core:database — Room database, base DAOs
├── config/
│   └── detekt/
│       └── detekt.yml      # Shared Detekt rules
├── scripts/
│   └── pre-commit          # Git pre-commit hook
└── gradle/
    └── libs.versions.toml  # Version catalog (single source of truth)
```

### Module Dependency Graph

```
:app
 └── :core:network      (when feature needs API calls)
 └── :core:database     (when feature needs local persistence)
```

Both `core` modules are **independent** — they do not depend on each other. Feature modules import only what they need.

## Getting Started

### Prerequisites

- Android Studio Ladybug or later
- JDK 11+
- Android SDK 36

### Setup

```bash
# 1. Clone the repository
git clone <repo-url> && cd Novaris

# 2. Install the git pre-commit hook
./gradlew installGitHook

# 3. Build the project
./gradlew assembleDebug
```

### Adding a Module Dependency

```kotlin
// In a feature module's build.gradle.kts
dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:database"))
}
```

## Code Quality

The project enforces code quality through **Detekt** (static analysis) and **ktlint** (formatting), both wired into a **git pre-commit hook**.

### Pre-commit Hook

On every `git commit`, the hook automatically:

1. **`ktlintFormat`** — auto-fixes formatting issues and re-stages changed files.
2. **`ktlintCheck`** — verifies no remaining formatting violations.
3. **`detekt`** — runs static analysis against the rules in `config/detekt/detekt.yml`.

If any step fails, the commit is blocked with a clear error message.

### Running Manually

```bash
# Format all files
./gradlew ktlintFormat

# Check formatting
./gradlew ktlintCheck

# Run Detekt analysis
./gradlew detekt
```

### Install / Reinstall the Hook

```bash
./gradlew installGitHook
```

The hook script lives at `scripts/pre-commit` and is copied into `.git/hooks/` by the Gradle task.

