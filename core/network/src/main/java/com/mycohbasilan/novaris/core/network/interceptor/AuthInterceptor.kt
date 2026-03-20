package com.mycohbasilan.novaris.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * An OkHttp [Interceptor] that appends authorization headers to every request.
 *
 * Inject this via Hilt and provide a token source. Pass an empty string
 * or `null` when no token is available — the header will simply be skipped.
 *
 * ```
 * @Provides fun provideAuthInterceptor(tokenStore: TokenStore) =
 *     AuthInterceptor { tokenStore.accessToken }
 * ```
 */
class AuthInterceptor(private val tokenProvider: () -> String?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = tokenProvider()

        val request =
            if (!token.isNullOrBlank()) {
                originalRequest
                    .newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
            } else {
                originalRequest
            }

        return chain.proceed(request)
    }
}
