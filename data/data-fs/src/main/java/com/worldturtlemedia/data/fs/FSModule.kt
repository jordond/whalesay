package com.worldturtlemedia.data.fs

import android.content.Context
import com.worldturtlemedia.data.fs.disk.CacheDiskManager
import com.worldturtlemedia.data.fs.disk.DiskManager
import com.worldturtlemedia.data.fs.util.Base64Decoder
import com.worldturtlemedia.data.fs.util.DefaultBase64Decoder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FSModule {

    @Provides @Singleton
    fun provideDiskManager(context: Context): DiskManager = CacheDiskManager(context)

    @Provides @Singleton
    fun provideBase64Decoder(): Base64Decoder = DefaultBase64Decoder
}
