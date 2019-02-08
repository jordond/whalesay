package ca.hoogit.whalesay.data.api.googlecloud.texttospeech.datasource

import ca.hoogit.whalesay.data.api.googlecloud.GoogleCloudAPI
import ca.hoogit.whalesay.data.api.googlecloud.GoogleCloudAPIModule
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechRequest
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechResponse
import ca.hoogit.whalesay.data.api.network.APIResponse
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
