package com.worldturtlemedia.whalesay.data.repository

import com.worldturtlemedia.whalesay.data.api.googlecloud.speechtotext.SpeechToTextDataSource
import javax.inject.Inject

class SpeechToTextRepository @Inject constructor(
    private val speechToTextDataSource: SpeechToTextDataSource
)
