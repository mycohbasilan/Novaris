# :core:network

Provides the shared networking layer for the entire app. Any module that needs to make API calls depends on this module.

## Dependencies

| Library | Scope | Purpose |
|---|---|---|
| Retrofit `2.11.0` | `api` | HTTP client / service interface definitions |
| Retrofit Kotlinx Serialization Converter `2.11.0` | `api` | JSON ↔ Kotlin data class conversion |
| Kotlin Serialization JSON `1.8.1` | `api` | JSON parsing engine |
| OkHttp `4.12.0` | `implementation` | Underlying HTTP transport |
| OkHttp Logging Interceptor `4.12.0` | `implementation` | Request/response logging for debug builds |
| Dagger Hilt `2.59.2` | `implementation` + KSP | Dependency injection |

> **`api` vs `implementation`:** Retrofit, its converter, and Kotlin Serialization are exposed as `api` so consuming modules can define their own Retrofit service interfaces and serializable models without re-declaring these dependencies.

## Package Structure

```
com.mycohbasilan.novaris.core.network
├── di/
│   └── NetworkModule.kt          # Hilt module — provides OkHttpClient, Retrofit, Json
├── ext/
│   └── ThrowableExt.kt           # Throwable.toUserMessage() for toast-friendly error strings
├── interceptor/
│   └── AuthInterceptor.kt        # OkHttp interceptor that appends Bearer tokens
├── model/
│   └── NetworkResult.kt          # Sealed interface: Success / Error / Exception
└── util/
    ├── JsonProvider.kt            # Shared Json instance with sensible defaults
    └── SafeApiCall.kt            # safeApiCall {} wrapper for Retrofit calls
```

## Key Components

### `NetworkModule` (Hilt)

Provides singleton instances of `Json`, `OkHttpClient`, and `Retrofit` scoped to `SingletonComponent`. Any module depending on `:core:network` can `@Inject` these directly.

### `safeApiCall`

A suspending wrapper that executes a Retrofit call and returns a `NetworkResult`:

```kotlin
val result = safeApiCall { api.getUser(id) }
when (result) {
    is NetworkResult.Success   -> handleData(result.data)
    is NetworkResult.Error     -> showError(result.code, result.message)
    is NetworkResult.Exception -> showToast(result.throwable.toUserMessage())
}
```

Only **recoverable** exceptions are caught (`IOException`, `HttpException`, `SerializationException`). Unexpected exceptions propagate to the caller so bugs surface immediately.

### `Throwable.toUserMessage()`

Extension function that maps common API exceptions to human-readable strings suitable for Toasts/Snackbars:

| Exception | Message |
|---|---|
| `UnknownHostException` | No internet connection… |
| `ConnectException` | Unable to connect to the server… |
| `SocketTimeoutException` | The request timed out… |
| `SSLException` | A secure connection could not be established… |
| `HttpException` | Status-code-specific (401 → "Session expired", 404 → "Not found", 5xx → "Server error", etc.) |
| `SerializationException` | Something went wrong while processing the response… |
| `IOException` (other) | A network error occurred… |

### `AuthInterceptor`

An OkHttp `Interceptor` that appends an `Authorization: Bearer <token>` header when a token is available. Provide it via Hilt with a token source lambda.

### `NetworkResult<T>`

```kotlin
sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val code: Int, val message: String?) : NetworkResult<Nothing>
    data class Exception(val throwable: Throwable) : NetworkResult<Nothing>
}
```

## Usage

```kotlin
// feature/build.gradle.kts
dependencies {
    implementation(project(":core:network"))
}

// Define a service interface in your feature module
interface UserApi {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Long): Response<UserDto>
}
```

