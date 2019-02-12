package com.worldturtlemedia.whalesay.data.db.texttospeech

import com.worldturtlemedia.whalesay.data.db.prefs.Prefs
import com.worldturtlemedia.whalesay.data.db.prefs.textToSpeechSettings
import javax.inject.Inject

class TextToSpeechDao @Inject constructor(private val prefs: Prefs) {

    val settings: TextToSpeechSettings
        get() = prefs.textToSpeechSettings()
}
