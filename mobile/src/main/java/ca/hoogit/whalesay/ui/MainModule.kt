package ca.hoogit.whalesay.ui

import androidx.lifecycle.ViewModel
import ca.hoogit.whalesay.core.di.scopes.ActivityScoped
import ca.hoogit.whalesay.core.di.viewmodel.ViewModelKey
import ca.hoogit.whalesay.features.about.AboutFeatureModule
import ca.hoogit.whalesay.features.onboarding.OnboardingFeatureModule
import ca.hoogit.whalesay.features.translate.TranslateFeatureModule
import ca.hoogit.whalesay.ui.main.MainActivity
import ca.hoogit.whalesay.ui.main.MainViewModel
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