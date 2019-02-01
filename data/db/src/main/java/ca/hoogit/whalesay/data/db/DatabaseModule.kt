package ca.hoogit.whalesay.data.db

import ca.hoogit.whalesay.data.db.prefs.PrefsModule
import dagger.Module

@Module(
    includes = [
        PrefsModule::class
    ]
)
abstract class DatabaseModule