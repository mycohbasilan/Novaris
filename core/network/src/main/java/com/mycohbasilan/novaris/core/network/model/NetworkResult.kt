package com.mycohbasilan.novaris.core.network.model

/**
 * A generic wrapper for network API responses.
 *
 * Usage:
 * ```
 * when (val result = safeApiCall { api.getUser(id) }) {
 *     is NetworkResult.Success -> handleData(result.data)
 *     is NetworkResult.Error   -> showError(result.code, result.message)
 *     is NetworkResult.Exception -> showException(result.throwable)
 * }
 * ```
 */
sealed interface NetworkResult<out T> {

    /** Successful response with [data]. */
    data class Success<T>(val data: T) : NetworkResult<T>

    /** HTTP error response with status [code] and optional [message]. */
    data class Error(val code: Int, val message: String?) : NetworkResult<Nothing>

    /** Non-HTTP failure (network timeout, parsing error, etc.). */
    data class Exception(val throwable: Throwable) : NetworkResult<Nothing>
}
