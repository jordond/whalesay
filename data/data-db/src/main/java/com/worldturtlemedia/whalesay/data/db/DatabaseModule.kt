package com.worldturtlemedia.whalesay.data.db

import com.worldturtlemedia.whalesay.data.db.prefs.PrefsModule
import dagger.Module

@Module(
    includes = [
        PrefsModule::class
    ]
)
abstract class DatabaseModule
