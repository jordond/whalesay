package ca.hoogit.whalesay.data.api.util

import ca.hoogit.whalesay.core.network.APIResult
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend inline fun <RESPONSE, RESULT> Deferred<Response<RESPONSE>>.awaitResult(
    crossinline transform: (response: RESPONSE) -> RESULT?
): APIResult<RESULT> {
    return try {
        val response = await()
        if (response.isSuccessful) {
            val body = response.body()
                ?: return APIResult.Error(NullPointerException("Response body was null"))

            transform(body)
                ?.let { APIResult.Success(it) }
                ?: APIResult.Error(IllegalArgumentException("Unable to transform response body!"))
        } else {
            APIResult.Error(HttpException(response), response.raw())
        }
    } catch (err: Exception) {
        APIResult.Error(IOException("Error await request", err))
    }
}

suspend fun <RESPONSE> Deferred<Response<RESPONSE>>.awaitResult():
    APIResult<RESPONSE> = awaitResult { it }