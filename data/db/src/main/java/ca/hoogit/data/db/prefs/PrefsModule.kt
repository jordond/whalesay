package ca.hoogit.data.db.prefs

import android.content.Context
import com.chibatching.kotpref.Kotpref
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal abstract class PrefsModule {

    @Provides @Singleton
    fun provideSharedPreferences(context: Context): Prefs {
        Kotpref.init(context)
        return Prefs
    }
}