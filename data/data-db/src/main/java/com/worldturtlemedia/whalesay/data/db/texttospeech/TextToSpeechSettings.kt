package com.worldturtlemedia.whalesay.data.db.texttospeech

import com.worldturtlemedia.whalesay.data.db.prefs.DebugPrefs

data class TextToSpeechSettings(
    val voice: String,
    val gender: String,
    val speakingRate: Float,
    val pitch: Float,
    val volumeGain: Float
)

fun DebugPrefs.textToSpeechSettings() = TextToSpeechSettings(
    voice = ttsVoice,
    gender = ttsGender,
    speakingRate = ttsSpeakingRate,
    pitch = ttsPitch,
    volumeGain = ttsVolumeGain
)
