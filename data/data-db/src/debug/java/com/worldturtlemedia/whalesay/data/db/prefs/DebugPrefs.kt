package com.worldturtlemedia.whalesay.data.db.prefs

import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.GENDER
import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.PITCH
import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.SPEAKING_RATE
import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.VOICE
import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.VOLUME_GAIN

object DebugPrefs : Prefs() {

    override val kotprefName: String = "debug_${super.kotprefName}"

    /* Text-to-speech settings */

    var hasSetDebugDefaults: Boolean by booleanPref(false)

    // TODO This is not working, switch to RemoteConfig
    // So it looks like Hyperion is having trouble changing these values...
    // So RemoteConfig is the better option I think.
    var ttsVoice: String by stringPref(VOICE)
    var ttsGender: String by stringPref(GENDER)
    var ttsSpeakingRate: Float by floatPref(SPEAKING_RATE)
    var ttsPitch: Float by floatPref(PITCH)
    var ttsVolumeGain: Float by floatPref(VOLUME_GAIN)
}

fun DebugPrefs.setDebugVoiceSettings() {
    if (hasSetDebugDefaults) return
    ttsVoice = VOICE
    ttsGender = GENDER
    ttsSpeakingRate = SPEAKING_RATE
    ttsPitch = PITCH
    ttsVolumeGain = VOLUME_GAIN
    hasSetDebugDefaults = true
}
