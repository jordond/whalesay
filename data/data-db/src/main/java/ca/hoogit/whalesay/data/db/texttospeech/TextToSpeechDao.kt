package ca.hoogit.whalesay.data.db.texttospeech

import ca.hoogit.whalesay.data.db.BuildConfig
import ca.hoogit.whalesay.data.db.prefs.Prefs
import javax.inject.Inject

class TextToSpeechDao @Inject constructor(private val prefs: Prefs) {

    val settings: TextToSpeechSettings
        get() = if (BuildConfig.DEBUG) prefs.textToSpeechSettings()
        else DefaultTextToSpeechSettings.INSTANCE
}
