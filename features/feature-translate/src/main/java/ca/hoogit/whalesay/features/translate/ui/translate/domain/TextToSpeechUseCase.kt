package ca.hoogit.whalesay.features.translate.ui.translate.domain

import android.content.Context
import ca.hoogit.whalesay.core.ktx.hasNoInternet
import ca.hoogit.whalesay.core.network.APIResult
import ca.hoogit.whalesay.core.network.isClientError
import ca.hoogit.whalesay.core.network.isServerError
import ca.hoogit.whalesay.data.repository.TextToSpeechRepository
import ca.hoogit.whalesay.features.translate.ui.error.model.ErrorType
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