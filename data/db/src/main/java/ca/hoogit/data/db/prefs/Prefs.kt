package ca.hoogit.data.db.prefs

import com.chibatching.kotpref.KotprefModel

object Prefs : KotprefModel() {

    override val kotprefName: String = "whalesay_prefs"

    var hasSeenOnboarding: Boolean by booleanPref(false)
}