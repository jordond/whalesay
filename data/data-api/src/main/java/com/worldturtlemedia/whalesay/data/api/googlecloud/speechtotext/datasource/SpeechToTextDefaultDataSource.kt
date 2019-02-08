package com.worldturtlemedia.whalesay.data.api.googlecloud.speechtotext.datasource

import com.worldturtlemedia.whalesay.data.api.googlecloud.speechtotext.SpeechToTextDataSource
import com.worldturtlemedia.whalesay.data.api.googlecloud.speechtotext.model.SpeechToTextRequest
import com.worldturtlemedia.whalesay.data.api.googlecloud.speechtotext.model.firstResult
import com.worldturtlemedia.whalesay.data.api.util.awaitResult
import javax.inject.Inject

class SpeechToTextDefaultDataSource @Inject constructor(
    private val speechToTextService: SpeechToTextService
) : SpeechToTextDataSource {

    override suspend fun recognizeSpeech(body: SpeechToTextRequest) =
        speechToTextService.recognizeSpeech(body).awaitResult { it.firstResult() }
}
