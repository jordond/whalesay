package com.worldturtlemedia.data.fs.audio

import androidx.annotation.WorkerThread
import com.worldturtlemedia.data.fs.disk.DiskManager
import com.worldturtlemedia.data.fs.util.Base64Decoder
import java.io.File
import javax.inject.Inject

class TextToSpeechAudioWriter @Inject constructor(
    private val diskManager: DiskManager,
    private val base64Decoder: Base64Decoder
) {

    @WorkerThread
    fun writeBase64AudioToDisk(data: String): File =
        diskManager.writeToDisk(FILENAME, base64Decoder.decode(data).toByteArray())

    @WorkerThread
    fun deleteAudioFile() {
        diskManager.deleteFromDisk(FILENAME)
    }

    companion object {

        const val FILENAME = "whalese-result.ogg"
    }
}
