package ca.hoogit.whalesay.data.api.googlecloud.texttospeech

import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechRequest
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechResponse
import ca.hoogit.whalesay.core.network.APIResult

interface TextToSpeechDataSource {

    suspend fun synthesizeTextToSpeech(body: TextToSpeechRequest): APIResult<TextToSpeechResponse>
}
