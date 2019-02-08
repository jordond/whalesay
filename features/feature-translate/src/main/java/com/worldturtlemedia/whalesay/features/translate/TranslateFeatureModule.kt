package com.worldturtlemedia.whalesay.features.translate

import com.worldturtlemedia.whalesay.features.translate.ui.error.di.ErrorModule
import com.worldturtlemedia.whalesay.features.translate.ui.translate.di.TranslateModule
import dagger.Module

@Module(
    includes = [
        TranslateModule::class,
        ErrorModule::class
    ]
)
abstract class TranslateFeatureModule
