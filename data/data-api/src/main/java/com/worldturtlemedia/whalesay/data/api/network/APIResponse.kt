package com.worldturtlemedia.whalesay.data.api.network

import kotlinx.coroutines.Deferred
import retrofit2.Response

typealias APIResponse<R> = Deferred<Response<R>>
