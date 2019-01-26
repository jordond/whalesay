package ca.hoogit.features.about

import ca.hoogit.features.about.ui.di.AboutModule
import dagger.Module

@Module(
    includes = [
        AboutModule::class
    ]
)
abstract class AboutFeatureModule