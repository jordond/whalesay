package com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.datasource

import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.TextToSpeechDataSource
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechRequest
import com.worldturtlemedia.whalesay.data.api.util.awaitResult
import javax.inject.Inject

class TextToSpeechDefaultDataSource @Inject constructor(
    private val textToSpeechService: TextToSpeechService
) : TextToSpeechDataSource {

    override suspend fun synthesizeTextToSpeech(body: TextToSpeechRequest) =
        textToSpeechService
            .convertTextToSpeech(body)
            .awaitResult()
}
