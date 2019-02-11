package com.worldturtlemedia.data.fs.audio

import androidx.annotation.WorkerThread
import com.worldturtlemedia.data.fs.disk.DiskManager
import com.worldturtlemedia.data.fs.util.Base64Decoder
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
    private val base64Decoder: Base64Decoder
) {

    /**
     * Write the base64 encoded audio string to the disk.
     *
     * @param[data] Base64 encoded audio string.
     */
    @WorkerThread
    fun writeBase64AudioToDisk(data: String): File =
        diskManager.writeToDisk(FILENAME, base64Decoder.decode(data).toByteArray())

    /**
     * Delete the audio file from the disk.
     */
    @WorkerThread
    fun deleteAudioFile() {
        diskManager.deleteFromDisk(FILENAME)
    }

    companion object {

        /**
         * Filename to use when writing the audio file.
         */
        const val FILENAME = "whalese-result.ogg"
    }
}
