package com.worldturtlemedia.whalesay.features.translate.ui.translate.domain

import android.content.Context
import com.worldturtlemedia.whalesay.core.ktx.hasNoInternet
import com.worldturtlemedia.whalesay.core.network.APIResult
import com.worldturtlemedia.whalesay.core.network.isClientError
import com.worldturtlemedia.whalesay.core.network.isServerError
import com.worldturtlemedia.whalesay.data.repository.TextToSpeechRepository
import com.worldturtlemedia.whalesay.features.translate.ui.error.model.ErrorType
import javax.inject.Inject

class TextToSpeechUseCase @Inject constructor(
    private val textToSpeechRepository: TextToSpeechRepository,
    private val context: Context
) {

    /**
     * Take the given text string and convert it to base64 encoded audio string
     *
     * @param[text] Text to convert to speech.
     * @return Base64 encoded audio string
     * @throws TextToSpeechException
     */
    @Throws
    suspend fun translateText(text: String): String {
        if (context.hasNoInternet()) throw TextToSpeechException(ErrorType.Network)

        val result = textToSpeechRepository.translateTextToSpeech(text)

        return when (result) {
            is APIResult.Success -> result.data.audioContent
            is APIResult.Error -> {
                throw TextToSpeechException(
                    when {
                        result.isClientError -> ErrorType.TextToSpeech
                        result.isServerError -> ErrorType.Google
                        else -> ErrorType.Generic
                    }
                )
            }
        }
    }
}

data class TextToSpeechException(val type: ErrorType) : Throwable()
