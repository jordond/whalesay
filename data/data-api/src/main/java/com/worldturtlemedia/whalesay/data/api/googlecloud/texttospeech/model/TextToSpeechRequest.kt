package com.worldturtlemedia.whalesay.data.api.googlecloud.texttospeech.model

data class TextToSpeechRequest(
    val input: VoiceInput,
    val voice: VoiceOptions = VoiceOptions(
        languageCode = "ja-JP"
    ),
    val audioConfig: AudioConfigOptions
)

data class VoiceInput(
    val text: String
)

data class VoiceOptions(
    val languageCode: String,
    val ssmlGender: String = "MALE"
)

data class AudioConfigOptions(
    val speakingRate: Float = 1.0f,
    val pitch: Float = 0f,
    val volumeGainDb: Float = 0f,
    val audioEncoding: String = "OGG_OPUS"
)
