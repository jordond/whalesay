package com.worldturtlemedia.whalesay.features.about

import com.worldturtlemedia.whalesay.features.about.ui.di.AboutModule
import dagger.Module

@Module(
    includes = [
        AboutModule::class
    ]
)
abstract class AboutFeatureModule
