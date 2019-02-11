package com.worldturtlemedia.whalesay.data.repository

import com.github.ajalt.timberkt.e
import com.worldturtlemedia.data.fs.audio.TextToSpeechAudioWriter
import com.worldturtlemedia.whalesay.core.coroutines.CoroutinesDispatcherProvider
import com.worldturtlemedia.whalesay.core.network.APIResult
import com.worldturtlemedia.whalesay.core.network.asAPIResultSuccess
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.TextToSpeechDataSource
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.AudioConfigOptions
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.TextToSpeechRequest
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.VoiceInput
import com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model.VoiceOptions
import com.worldturtlemedia.whalesay.data.db.texttospeech.TextToSpeechDao
import com.worldturtlemedia.whalesay.data.db.texttospeech.TextToSpeechSettings
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TextToSpeechRepository @Inject constructor(
    private val textToSpeechDataSource: TextToSpeechDataSource,
    private val textToSpeechDao: TextToSpeechDao,
    private val textToSpeechAudioWriter: TextToSpeechAudioWriter,
    private val dispatcher: CoroutinesDispatcherProvider
) {

    suspend fun translateTextToSpeech(
        input: String,
        settings: TextToSpeechSettings = textToSpeechDao.settings
    ): APIResult<File> {
        val result = textToSpeechDataSource.synthesizeTextToSpeech(
            createPayload(input, settings)
        )

        return when (result) {
            is APIResult.Success -> writeAudioToDisk(result.data.audioContent)
            is APIResult.Error -> result
        }
    }

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

    private suspend fun writeAudioToDisk(base64String: String): APIResult<File> = try {
        withContext(dispatcher.io) {
            textToSpeechAudioWriter.writeBase64AudioToDisk(base64String).asAPIResultSuccess()
        }
    } catch (error: Throwable) {
        e(error) { "Unable to save audio string to disk!" }
        APIResult.Error(WriteAudioToDiskException)
    }
}

object WriteAudioToDiskException : Throwable()
