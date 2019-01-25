package ca.hoogit.ktx

import android.net.Uri
import timber.log.Timber

fun String.toURI(): Uri? {
    return try {
        Uri.parse(this)
    } catch (throwable: Throwable) {
        Timber.e(throwable, "Unable to parse $this")
        null
    }
}