package com.worldturtlemedia.data.fs.disk

import androidx.annotation.WorkerThread
import java.io.File
import java.io.FileOutputStream

/**
 * Use to write data to the disk.
 */
interface DiskManager {

    /**
     * Root directory [File] to write the files to.
     *
     * @see writeToDisk
     */
    val directory: File

    /**
     * Write data to the disk.
     *
     * @param[filename] Filename to for the file.
     * @param[data] ByteArray data to write to the disk.
     * @param[append] If true it will append the data to the file.
     * @return[File] Reference to the newly written file.
     */
    @WorkerThread
    fun writeToDisk(filename: String, data: ByteArray, append: Boolean = false): File =
        File(directory, filename).also { file ->
            FileOutputStream(file, append).use { it.write(data) }
        }

    /**
     * Delete a file from the disk.
     *
     * @param[filename] Filename of file to delete.
     */
    @WorkerThread
    fun deleteFromDisk(filename: String) {
        File(directory, filename).delete()
    }
}
