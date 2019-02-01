package ca.hoogit.whalesay.data.api.api.googlecloud.texttospeech.datasource

import ca.hoogit.whalesay.data.api.api.googlecloud.texttospeech.model.TextToSpeechRequest
import ca.hoogit.whalesay.data.api.api.googlecloud.texttospeech.model.TextToSpeechResponse
import ca.hoogit.whalesay.data.api.network.APIResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface TextToSpeechService {

    @POST("text:synthesize")
    fun convertTextToSpeech(
        @Query("key") key: String,
        @Body body: TextToSpeechRequest
    ): APIResponse<TextToSpeechResponse>
}