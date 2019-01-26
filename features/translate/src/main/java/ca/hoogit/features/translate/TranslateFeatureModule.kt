package ca.hoogit.features.translate

import ca.hoogit.features.translate.ui.translate.di.TranslateModule
import dagger.Module

@Module(
    includes = [
        TranslateModule::class
    ]
)
abstract class TranslateFeatureModule