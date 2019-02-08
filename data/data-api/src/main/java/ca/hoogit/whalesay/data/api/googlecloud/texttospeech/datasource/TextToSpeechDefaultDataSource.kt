package ca.hoogit.whalesay.data.api.googlecloud.texttospeech.datasource

import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.TextToSpeechDataSource
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechRequest
import ca.hoogit.whalesay.data.api.util.awaitResult
import javax.inject.Inject

class TextToSpeechDefaultDataSource @Inject constructor(
    private val textToSpeechService: TextToSpeechService
) : TextToSpeechDataSource {

    override suspend fun synthesizeTextToSpeech(body: TextToSpeechRequest) =
        textToSpeechService
            .convertTextToSpeech(body)
            .awaitResult()
}
