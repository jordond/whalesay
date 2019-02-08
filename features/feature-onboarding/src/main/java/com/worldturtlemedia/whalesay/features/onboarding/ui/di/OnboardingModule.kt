package com.worldturtlemedia.whalesay.features.onboarding.ui.di

import androidx.lifecycle.ViewModel
import com.worldturtlemedia.whalesay.core.di.scopes.FragmentScoped
import com.worldturtlemedia.whalesay.core.di.viewmodel.ViewModelKey
import com.worldturtlemedia.whalesay.features.onboarding.ui.OnboardingFragment
import com.worldturtlemedia.whalesay.features.onboarding.ui.OnboardingViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class OnboardingModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeOnboardingFragment(): OnboardingFragment

    @Binds @IntoMap
    @ViewModelKey(OnboardingViewModel::class)
    abstract fun bindOnboardingViewModel(viewModel: OnboardingViewModel): ViewModel
}
