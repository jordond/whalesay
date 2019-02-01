package ca.hoogit.whalesay.data.api.util

import ca.hoogit.whalesay.data.api.network.APIResult
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend inline fun <RESPONSE, RESULT> Deferred<Response<RESPONSE>>.awaitResult(
    crossinline transform: (response: RESPONSE) -> RESULT
): APIResult<RESULT> {
    return try {
        val response = await()
        if (response.isSuccessful) {
            response.body()
                ?.let { APIResult.Success(transform(it)) }
                ?: APIResult.Error(NullPointerException("Response body was null"))
        } else {
            APIResult.Error(HttpException(response), response.raw())
        }
    } catch (err: Exception) {
        APIResult.Error(IOException("Error await request", err))
    }
}

suspend fun <RESPONSE> Deferred<Response<RESPONSE>>.awaitResult():
    APIResult<RESPONSE> = awaitResult { it }