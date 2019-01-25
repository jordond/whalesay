package ca.hoogit.core.network

import kotlinx.coroutines.Deferred
import retrofit2.Response

typealias APIResult<R> = Deferred<Response<R>>