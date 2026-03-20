package com.mycohbasilan.novaris.core.network.ext

import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import kotlinx.serialization.SerializationException
import retrofit2.HttpException

/**
 * Maps a [Throwable] to a human-readable error message suitable for
 * displaying in a Toast or Snackbar.
 *
 * Covers the most common failure scenarios when making API calls:
 * network unavailability, timeouts, SSL issues, HTTP errors,
 * serialization problems, and unexpected exceptions.
 *
 * Usage:
 * ```
 * is NetworkResult.Exception -> showToast(result.throwable.toUserMessage())
 * ```
 */
fun Throwable.toUserMessage(): String = when (this) {
    is UnknownHostException ->
        "No internet connection. Please check your network and try again."

    is ConnectException ->
        "Unable to connect to the server. Please try again later."

    is SocketTimeoutException ->
        "The request timed out. Please check your connection and try again."

    is SSLException ->
        "A secure connection could not be established. Please try again later."

    is HttpException -> toHttpErrorMessage()

    is SerializationException ->
        "Something went wrong while processing the response. Please try again."

    is IOException ->
        "A network error occurred. Please check your connection and try again."

    else ->
        localizedMessage ?: "An unexpected error occurred. Please try again."
}

/**
 * Translates an [HttpException] status code into a user-friendly message.
 */
private fun HttpException.toHttpErrorMessage(): String = when (code()) {
    HttpStatusCode.BAD_REQUEST -> "Bad request. Please check your input and try again."
    HttpStatusCode.UNAUTHORIZED -> "Your session has expired. Please log in again."
    HttpStatusCode.FORBIDDEN -> "You don't have permission to perform this action."
    HttpStatusCode.NOT_FOUND -> "The requested resource was not found."
    HttpStatusCode.REQUEST_TIMEOUT -> "The request timed out. Please try again."
    HttpStatusCode.CONFLICT -> "A conflict occurred. Please refresh and try again."
    HttpStatusCode.UNPROCESSABLE_ENTITY -> "The request could not be processed. Please verify your input."
    HttpStatusCode.TOO_MANY_REQUESTS -> "Too many requests. Please wait a moment and try again."
    in HttpStatusCode.INTERNAL_SERVER_ERROR..HttpStatusCode.SERVER_ERROR_RANGE_END ->
        "A server error occurred. Please try again later."
    else -> "Something went wrong (HTTP ${code()}). Please try again."
}

private object HttpStatusCode {
    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val REQUEST_TIMEOUT = 408
    const val CONFLICT = 409
    const val UNPROCESSABLE_ENTITY = 422
    const val TOO_MANY_REQUESTS = 429
    const val INTERNAL_SERVER_ERROR = 500
    const val SERVER_ERROR_RANGE_END = 599
}
