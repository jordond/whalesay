package com.worldturtlemedia.whalesay.features.onboarding

import com.worldturtlemedia.whalesay.features.onboarding.ui.di.OnboardingModule
import dagger.Module

@Module(
    includes = [
        OnboardingModule::class
    ]
)
abstract class OnboardingFeatureModule
