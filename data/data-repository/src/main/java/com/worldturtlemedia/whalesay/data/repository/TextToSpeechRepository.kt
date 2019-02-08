package com.worldturtlemedia.whalesay.data.repository

import com.worldturtlemedia.whalesay.core.network.APIResult
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.TextToSpeechDataSource
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.AudioConfigOptions
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechRequest
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechResponse
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.VoiceInput
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.VoiceOptions
import com.worldturtlemedia.whalesay.data.db.texttospeech.TextToSpeechDao
import com.worldturtlemedia.whalesay.data.db.texttospeech.TextToSpeechSettings
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
