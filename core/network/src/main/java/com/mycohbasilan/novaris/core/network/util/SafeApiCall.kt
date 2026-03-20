package com.mycohbasilan.novaris.core.network.util

import com.mycohbasilan.novaris.core.network.model.NetworkResult
import java.io.IOException
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import retrofit2.Response

/**
 * Executes a Retrofit [apiCall] inside a try-catch and wraps the result
 * in a [NetworkResult].
 *
 * - Returns [NetworkResult.Success] when the HTTP response is successful.
 * - Returns [NetworkResult.Error] for non-2xx HTTP responses.
 * - Returns [NetworkResult.Exception] for known, recoverable exceptions
 *   ([IOException], [HttpException], [SerializationException]).
 *
 * Unexpected exceptions are **not** caught and will propagate to the caller
 * so they can be detected and fixed early.
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> = try {
    val response = apiCall()
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(response.code(), "Response body is null")
        }
    } else {
        NetworkResult.Error(response.code(), response.errorBody()?.string())
    }
} catch (e: IOException) {
    NetworkResult.Exception(e)
} catch (e: HttpException) {
    NetworkResult.Exception(e)
} catch (e: SerializationException) {
    NetworkResult.Exception(e)
}
