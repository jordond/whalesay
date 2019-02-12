package com.worldturtlemedia.data.fs.audio

import com.worldturtlemedia.data.fs.disk.DiskManager
import com.worldturtlemedia.data.fs.util.Base64Decoder
import com.worldturtlemedia.whalesay.core.coroutines.CoroutinesDispatcherProvider
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

/**
 * Writes Base64 encoded audio string to the cache folder.
 *
 * @param[diskManager] Abstracted disk writing utility.
 * @param[base64Decoder] Implementation for decoding Base64.
 */
class TextToSpeechAudioWriter @Inject constructor(
    private val diskManager: DiskManager,
    private val base64Decoder: Base64Decoder,
    private val dispatcher: CoroutinesDispatcherProvider
) {

    /**
     * Write the base64 encoded audio string to the disk.
     *
     * @param[data] Base64 encoded audio string.
     */
    suspend fun writeBase64AudioToDisk(data: String): File = withContext(dispatcher.io) {
        diskManager.writeToDisk(FILENAME, base64Decoder.decode(data))
    }

    /**
     * Delete the audio file from the disk.
     */
    suspend fun deleteAudioFile() = withContext(dispatcher.io) {
        diskManager.deleteFromDisk(FILENAME)
    }

    companion object {

        /**
         * Filename to use when writing the audio file.
         */
        const val FILENAME = "whalese-result.ogg"
    }
}
