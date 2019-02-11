package com.worldturtlemedia.data.fs.util

import android.util.Base64
import java.util.Base64 as UtilBase64

/**
 * Used to provide Base64 decoding.
 *
 * This is required because [UtilBase64.getDecoder] requires API 26.  So to avoid needing
 * instrumented tests, we can supply a mock [Base64Decoder] interface in our unit tests.
 */
interface Base64Decoder {

    /**
     * Decodes a base64 encoded string into a regular string.
     *
     * @param[base64String] Base64 encoded string.
     * @param[flags] Decoding flags, see [Base64.decode].
     * @return Decoded string.
     */
    fun decode(base64String: String, flags: Int = Base64.DEFAULT): String
}

/**
 * Default implementation of [Base64Decoder], to be used by the application.
 *
 * Unit tests should extend [Base64Decoder] or mock the interface.
 */
object DefaultBase64Decoder : Base64Decoder {

    /**
     * Decode the string using the Android's [Base64.decode].
     *
     * @param[base64String] Base64 encoded string.
     * @param[flags] Decoding flags, see [Base64.decode].
     * @return Decoded string.
     */
    override fun decode(base64String: String, flags: Int): String =
        Base64.decode(base64String, flags).toString()
}
