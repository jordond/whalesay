package ca.hoogit.whalesay.features.onboarding

import ca.hoogit.whalesay.features.onboarding.ui.di.OnboardingModule
import dagger.Module

@Module(
    includes = [
        OnboardingModule::class
    ]
)
abstract class OnboardingFeatureModule
