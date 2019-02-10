package com.worldturtlemedia.data.fs.disk

import android.content.Context
import java.io.File
import javax.inject.Inject

/**
 * Cache disk implementation of [DiskManager], used for writing files to the cache folder.
 *
 * @param[context] Android context used for getting the cache folder.
 */
class CacheDiskManager @Inject constructor(context: Context) : DiskManager {

    override val directory: File = context.cacheDir
}
