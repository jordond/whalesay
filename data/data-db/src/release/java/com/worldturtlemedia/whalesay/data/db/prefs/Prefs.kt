package com.worldturtlemedia.whalesay.data.db.prefs

import com.worldturtlemedia.whalesay.data.db.texttospeech.DefaultTextToSpeechSettings

/**
 * Release implementation of [SharedPrefs].
 *
 * Useful for Release specific Shared preferences.
 */
object Prefs : SharedPrefs()

@Suppress("unused")
fun Prefs.textToSpeechSettings() = DefaultTextToSpeechSettings.INSTANCE
