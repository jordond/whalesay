package com.worldturtlemedia.whalesay.data.db.texttospeech

object DefaultTextToSpeechSettings {

    const val VOICE = "ja-JP"
    const val GENDER = "MALE"
    const val SPEAKING_RATE = 0.25f
    const val PITCH = -20f
    const val VOLUME_GAIN = 0f

    val INSTANCE = TextToSpeechSettings(VOICE, GENDER, SPEAKING_RATE, PITCH, VOLUME_GAIN)
}
