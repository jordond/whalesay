package com.worldturtlemedia.whalesay.data.db.prefs

import com.chibatching.kotpref.KotprefModel

interface PrefsValues {

    var hasSeenOnboarding: Boolean
}

open class Prefs : KotprefModel(), PrefsValues {

    override val kotprefName: String = "whalesay_prefs"

    override var hasSeenOnboarding: Boolean by booleanPref(false)
}
