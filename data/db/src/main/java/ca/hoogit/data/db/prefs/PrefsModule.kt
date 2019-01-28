package ca.hoogit.data.db.prefs

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class PrefsModule {

    @Provides @Singleton
    fun provideSharedPreferences(): Prefs = Prefs
}