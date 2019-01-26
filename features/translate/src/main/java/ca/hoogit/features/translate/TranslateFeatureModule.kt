package ca.hoogit.features.translate

import ca.hoogit.features.translate.ui.error.di.ErrorModule
import ca.hoogit.features.translate.ui.translate.di.TranslateModule
import dagger.Module

@Module(
    includes = [
        TranslateModule::class,
        ErrorModule::class
    ]
)
abstract class TranslateFeatureModule