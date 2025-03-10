package com.example.unihub.core.data.networking

import com.example.unihub.core.domain.util.NetworkError
import com.example.unihub.core.domain.util.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(data = response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Result.Error(error = NetworkError.SERIALIZATION)
            }
        }

        408 -> Result.Error(error = NetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(error = NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(error = NetworkError.SERVER_ERROR)
        else -> Result.Error(error = NetworkError.UNKNOWN)
    }
}