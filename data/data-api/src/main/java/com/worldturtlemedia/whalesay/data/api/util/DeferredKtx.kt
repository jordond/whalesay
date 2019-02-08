package com.worldturtlemedia.whalesay.data.api.util

import com.github.ajalt.timberkt.d
import com.worldturtlemedia.whalesay.core.network.APIResult
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
        APIResult.Error(IOException("Error awaiting the API request", err))
    }.also { result ->
        if (result is APIResult.Error) {
            val url = result.response?.request()?.url() ?: return@also
            d { "Request to -> $url failed" }
        }
    }
}

suspend fun <RESPONSE> Deferred<Response<RESPONSE>>.awaitResult():
    APIResult<RESPONSE> = awaitResult { it }
