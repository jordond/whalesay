package ca.hoogit.whalesay.data.api.api.googlecloud.texttospeech

import javax.inject.Singleton

@Singleton
class TextToSpeechRepository constructor(
    private val textToSpeechDataSource: TextToSpeechDataSource
)