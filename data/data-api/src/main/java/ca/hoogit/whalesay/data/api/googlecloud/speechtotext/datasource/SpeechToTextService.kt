package ca.hoogit.whalesay.data.api.googlecloud.speechtotext.datasource

import ca.hoogit.whalesay.data.api.googlecloud.speechtotext.model.SpeechToTextRequest
import ca.hoogit.whalesay.data.api.googlecloud.speechtotext.model.SpeechToTextResponse
import ca.hoogit.whalesay.data.api.network.APIResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SpeechToTextService {

    @POST("speech:recognize")
    fun recognizeSpeech(@Body body: SpeechToTextRequest): APIResponse<SpeechToTextResponse>
}