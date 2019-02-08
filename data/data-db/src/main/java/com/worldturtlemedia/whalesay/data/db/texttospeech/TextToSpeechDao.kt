package com.worldturtlemedia.whalesay.data.db.texttospeech

import com.worldturtlemedia.whalesay.data.db.BuildConfig
import com.worldturtlemedia.whalesay.data.db.prefs.Prefs
import javax.inject.Inject

class TextToSpeechDao @Inject constructor(private val prefs: Prefs) {

    val settings: TextToSpeechSettings
        get() = if (BuildConfig.DEBUG) prefs.textToSpeechSettings()
        else DefaultTextToSpeechSettings.INSTANCE
}
