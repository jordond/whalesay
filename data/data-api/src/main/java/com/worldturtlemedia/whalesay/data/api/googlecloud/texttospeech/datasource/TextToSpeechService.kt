package com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.datasource

import com.worldturtlemedia.whalesay.data.api.googlecloud.GoogleCloudAPI
import com.worldturtlemedia.whalesay.data.api.googlecloud.GoogleCloudAPIModule
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechRequest
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechResponse
import com.worldturtlemedia.whalesay.data.api.network.APIResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TextToSpeechService {

    /**
     * The GoogleCloud Text-To-Speech api endpoint
     *
     * The api key is auto injected as a OkHttp interceptor, see [GoogleCloudAPI.AddGoogleCloudKeyParamInterceptor],
     * and [GoogleCloudAPIModule.provideGoogleCloudAPIKey].
     *
     * @param[body] Text-to-speech options
     */
    @POST("text:synthesize")
    fun convertTextToSpeech(@Body body: TextToSpeechRequest): APIResponse<TextToSpeechResponse>
}
