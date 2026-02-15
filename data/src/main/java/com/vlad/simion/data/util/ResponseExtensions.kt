package com.vlad.simion.data.util

import com.vlad.simion.domain.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T : Any, R : Any> executeSafely(
    apiCall: suspend () -> Response<T>,
    transform: (T) -> R
): Result<R> = withContext(Dispatchers.IO) {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Result.Success(transform(body))
            } else {
                Result.Error(
                    message = "Response body is null",
                    code = response.code()
                )
            }
        } else {
            Result.Error(
                message = response.message() ?: "Unknown error",
                code = response.code()
            )
        }
    } catch (e: Exception) {
        Result.Error(
            message = e.message ?: "Network error",
            code = null
        )
    }
}
