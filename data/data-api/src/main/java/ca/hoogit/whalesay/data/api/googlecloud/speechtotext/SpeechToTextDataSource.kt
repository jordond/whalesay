package ca.hoogit.whalesay.data.api.googlecloud.speechtotext

import ca.hoogit.whalesay.core.network.APIResult
import ca.hoogit.whalesay.data.api.googlecloud.speechtotext.model.Alternative
import ca.hoogit.whalesay.data.api.googlecloud.speechtotext.model.SpeechToTextRequest

/**
 * TODO: Move to the repository module?
 */
interface SpeechToTextDataSource {

    suspend fun recognizeSpeech(body: SpeechToTextRequest): APIResult<Alternative>
}