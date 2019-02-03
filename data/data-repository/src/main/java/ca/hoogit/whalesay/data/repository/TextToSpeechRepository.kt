package ca.hoogit.whalesay.data.repository

import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.TextToSpeechDataSource
import javax.inject.Singleton

@Singleton
class TextToSpeechRepository constructor(
    private val textToSpeechDataSource: TextToSpeechDataSource
)