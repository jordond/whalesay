package ca.hoogit.whalesay.features.about

import ca.hoogit.whalesay.features.about.ui.di.AboutModule
import dagger.Module

@Module(
    includes = [
        AboutModule::class
    ]
)
abstract class AboutFeatureModule
