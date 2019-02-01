package ca.hoogit.whalesay.features.translate

import ca.hoogit.whalesay.features.translate.ui.error.di.ErrorModule
import ca.hoogit.whalesay.features.translate.ui.translate.di.TranslateModule
import dagger.Module

@Module(
    includes = [
        TranslateModule::class,
        ErrorModule::class
    ]
)
abstract class TranslateFeatureModule