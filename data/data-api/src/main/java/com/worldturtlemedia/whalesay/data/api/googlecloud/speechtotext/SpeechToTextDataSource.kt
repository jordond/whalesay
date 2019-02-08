package com.worldturtlemedia.whalesay.data.api.googlecloud.speechtotext

import com.worldturtlemedia.whalesay.core.network.APIResult
import com.worldturtlemedia.whalesay.data.api.googlecloud.speechtotext.model.Alternative
import com.worldturtlemedia.whalesay.data.api.googlecloud.speechtotext.model.SpeechToTextRequest

/**
 * TODO: Move to the repository module?
 */
interface SpeechToTextDataSource {

    suspend fun recognizeSpeech(body: SpeechToTextRequest): APIResult<Alternative>
}
