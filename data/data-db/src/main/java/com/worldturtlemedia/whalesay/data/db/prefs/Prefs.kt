package com.worldturtlemedia.whalesay.data.db.prefs

import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.GENDER
import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.PITCH
import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.SPEAKING_RATE
import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.VOICE
import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.VOLUME_GAIN
import com.chibatching.kotpref.KotprefModel

object Prefs : KotprefModel() {

    override val kotprefName: String = "whalesay_prefs"

    var hasSeenOnboarding: Boolean by booleanPref(false)

    /* Text-to-speech settings */

    var ttsVoice: String by stringPref(VOICE)
    var ttsGender: String by stringPref(GENDER)
    var ttsSpeakingRate: Float by floatPref(SPEAKING_RATE)
    var ttsPitch: Float by floatPref(PITCH)
    var ttsVolumeGain: Float by floatPref(VOLUME_GAIN)
}
