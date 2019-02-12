package com.worldturtlemedia.whalesay.data.db.texttospeech

data class TextToSpeechSettings(
    val voice: String,
    val gender: String,
    val speakingRate: Float,
    val pitch: Float,
    val volumeGain: Float
)
