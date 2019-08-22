package com.worldturtlemedia.data.fs.util

import android.util.Base64
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
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
     * @return Decoded string as a ByteArray.
     */
    fun decode(
        base64String: String,
        charset: Charset = StandardCharsets.ISO_8859_1,
        flags: Int = Base64.DEFAULT
    ): ByteArray
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
    override fun decode(
        base64String: String,
        charset: Charset,
        flags: Int
    ): ByteArray = Base64.decode(base64String.toByteArray(StandardCharsets.ISO_8859_1), flags)
}
