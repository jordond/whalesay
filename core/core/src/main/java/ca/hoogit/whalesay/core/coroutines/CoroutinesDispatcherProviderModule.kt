package ca.hoogit.whalesay.core.coroutines

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class CoroutinesDispatcherProviderModule {

    @Provides
    fun provideCoroutinesDispatcherProvider() = CoroutinesDispatcherProvider(
        Dispatchers.Main,
        Dispatchers.Default,
        Dispatchers.IO
    )
}