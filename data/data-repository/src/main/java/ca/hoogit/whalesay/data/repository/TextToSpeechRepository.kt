package ca.hoogit.whalesay.data.repository

import ca.hoogit.whalesay.core.network.APIResult
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.TextToSpeechDataSource
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model.AudioConfigOptions
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechRequest
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechResponse
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model.VoiceInput
import ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model.VoiceOptions
import ca.hoogit.whalesay.data.db.texttospeech.TextToSpeechDao
import ca.hoogit.whalesay.data.db.texttospeech.TextToSpeechSettings
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TextToSpeechRepository @Inject constructor(
    private val textToSpeechDataSource: TextToSpeechDataSource,
    private val textToSpeechDao: TextToSpeechDao
) {

    suspend fun translateTextToSpeech(
        input: String,
        settings: TextToSpeechSettings = textToSpeechDao.settings
    ): APIResult<TextToSpeechResponse> =
        textToSpeechDataSource.synthesizeTextToSpeech(createPayload(input, settings))

    private fun createPayload(
        input: String,
        settings: TextToSpeechSettings
    ): TextToSpeechRequest = settings.run {
        TextToSpeechRequest(
            input = VoiceInput(input),
            voice = VoiceOptions(languageCode = voice, ssmlGender = gender),
            audioConfig = AudioConfigOptions(
                speakingRate = speakingRate,
                pitch = pitch,
                volumeGainDb = volumeGain
            )
        )
    }
}