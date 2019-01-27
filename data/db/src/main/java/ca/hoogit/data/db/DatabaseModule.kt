package ca.hoogit.data.db

import ca.hoogit.data.db.prefs.PrefsModule
import dagger.Module

@Module(
    includes = [
        PrefsModule::class
    ]
)
abstract class DatabaseModule