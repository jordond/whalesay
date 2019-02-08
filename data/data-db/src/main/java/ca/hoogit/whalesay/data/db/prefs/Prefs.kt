package ca.hoogit.whalesay.data.db.prefs

import ca.hoogit.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.GENDER
import ca.hoogit.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.PITCH
import ca.hoogit.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.SPEAKING_RATE
import ca.hoogit.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.VOICE
import ca.hoogit.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings.VOLUME_GAIN
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
