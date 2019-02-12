package com.worldturtlemedia.whalesay.data.db.prefs

import com.worldturtlemedia.whalesay.data.db.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class PrefsModule {

    @Provides @Singleton
    fun provideSharedPreferences(): Prefs = if (BuildConfig.DEBUG) DebugPrefs else Prefs()
}
