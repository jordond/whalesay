package com.worldturtlemedia.whalesay.data.db.prefs

import com.chibatching.kotpref.KotprefModel

/**
 * Base SharedPreferences [KotprefModel] class.
 *
 * Used to create custom variant-based shared preference models.
 */
abstract class SharedPrefs : KotprefModel() {

    override val kotprefName: String = "whalesay_prefs"

    var hasSeenOnboarding: Boolean by booleanPref(false)
}
