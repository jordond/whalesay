package ca.hoogit.whalesay.data.repository

import ca.hoogit.whalesay.data.api.googlecloud.speechtotext.SpeechToTextDataSource
import javax.inject.Inject

class SpeechToTextRepository @Inject constructor(
    private val speechToTextDataSource: SpeechToTextDataSource
)