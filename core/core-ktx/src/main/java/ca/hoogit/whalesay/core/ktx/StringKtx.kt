package ca.hoogit.whalesay.core.ktx

import android.net.Uri
import timber.log.Timber

fun Char.repeat(count: Int) = toString().repeat(count)

fun String.toURI(): Uri? {
    return try {
        Uri.parse(this)
    } catch (throwable: Throwable) {
        Timber.e(throwable, "Unable to parse $this")
        null
    }
}
