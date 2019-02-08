package com.worldturtlemedia.whalesay.ui

import androidx.lifecycle.ViewModel
import com.worldturtlemedia.whalesay.core.di.scopes.ActivityScoped
import com.worldturtlemedia.whalesay.core.di.viewmodel.ViewModelKey
import com.worldturtlemedia.whalesay.features.about.AboutFeatureModule
import com.worldturtlemedia.whalesay.features.onboarding.OnboardingFeatureModule
import com.worldturtlemedia.whalesay.features.translate.TranslateFeatureModule
import com.worldturtlemedia.whalesay.ui.main.MainActivity
import com.worldturtlemedia.whalesay.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MainModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            OnboardingFeatureModule::class,
            TranslateFeatureModule::class,
            AboutFeatureModule::class
        ]
    )
    abstract fun mainActivity(): MainActivity

    @Binds @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
