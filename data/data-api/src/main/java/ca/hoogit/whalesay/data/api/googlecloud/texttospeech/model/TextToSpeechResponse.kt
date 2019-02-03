package ca.hoogit.whalesay.data.api.googlecloud.texttospeech.model

import android.util.Base64

data class TextToSpeechResponse(
    val audioContent: String
) {

    val decoded: String
        get() = Base64.decode(audioContent, Base64.DEFAULT).toString()
}