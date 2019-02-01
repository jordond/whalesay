package ca.hoogit.whalesay.data.api.di

import ca.hoogit.whalesay.data.api.network.adapters.ApplicationJsonAdapterFactory
import ca.hoogit.whalesay.data.api.network.adapters.EnvelopeFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class MoshiModule {

    @Provides @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(ApplicationJsonAdapterFactory.INSTANCE)
            .add(EnvelopeFactory.INSTANCE)
            .build()
}
