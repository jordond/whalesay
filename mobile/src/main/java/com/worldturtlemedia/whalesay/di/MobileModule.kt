package com.worldturtlemedia.whalesay.di

import android.content.Context
import com.worldturtlemedia.whalesay.MainApplication
import dagger.Module
import dagger.Provides

@Module
class MobileModule {

    @Provides
    fun provideContext(application: MainApplication): Context = application.applicationContext
}
