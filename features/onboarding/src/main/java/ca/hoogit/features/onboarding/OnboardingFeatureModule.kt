package ca.hoogit.features.onboarding

import ca.hoogit.features.onboarding.ui.di.OnboardingModule
import dagger.Module

@Module(
    includes = [
        OnboardingModule::class
    ]
)
abstract class OnboardingFeatureModule