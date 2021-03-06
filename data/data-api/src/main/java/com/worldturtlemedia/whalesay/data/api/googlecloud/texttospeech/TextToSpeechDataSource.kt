package com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech

import com.worldturtlemedia.whalesay.core.network.APIResult
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechRequest
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechResponse

interface TextToSpeechDataSource {

    suspend fun synthesizeTextToSpeech(body: TextToSpeechRequest): APIResult<TextToSpeechResponse>
}
