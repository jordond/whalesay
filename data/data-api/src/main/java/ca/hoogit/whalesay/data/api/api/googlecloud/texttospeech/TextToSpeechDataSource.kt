package ca.hoogit.whalesay.data.api.api.googlecloud.texttospeech

import ca.hoogit.whalesay.data.api.api.googlecloud.texttospeech.model.TextToSpeechRequest
import ca.hoogit.whalesay.data.api.api.googlecloud.texttospeech.model.TextToSpeechResponse
import ca.hoogit.whalesay.data.api.network.APIResult

interface TextToSpeechDataSource {

    suspend fun synthesizeTextToSpeech(
        apiKey: String,
        body: TextToSpeechRequest
    ): APIResult<TextToSpeechResponse>
}