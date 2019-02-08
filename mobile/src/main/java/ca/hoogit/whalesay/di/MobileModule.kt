package ca.hoogit.whalesay.di

import android.content.Context
import ca.hoogit.whalesay.MainApplication
import dagger.Module
import dagger.Provides

@Module
class MobileModule {

    @Provides
    fun provideContext(application: MainApplication): Context = application.applicationContext
}
