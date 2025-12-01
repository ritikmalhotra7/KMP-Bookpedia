package com.sample.bookpedia.core.data.remote

import com.sample.bookpedia.core.domain.DataError
import com.sample.bookpedia.core.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Result.Error(DataError.Remote.SERIALIZATION_ERROR)
            }
        }

        408 -> Result.Error(DataError.Remote.REQUEST_TIME_OUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Remote.SERVER_ERROR)
        else -> Result.Error(DataError.Remote.UNKNOWN_ERROR)
    }
}

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: Exception) {
        return Result.Error(
            when (e) {
                is SocketTimeoutException -> DataError.Remote.REQUEST_TIME_OUT
                is UnresolvedAddressException -> DataError.Remote.NO_INTERNET
                else -> {
                    coroutineContext.ensureActive()
                    DataError.Remote.UNKNOWN_ERROR
                }
            }
        )
    }
    return responseToResult(response)
}