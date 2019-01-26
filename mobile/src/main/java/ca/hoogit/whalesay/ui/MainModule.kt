package ca.hoogit.whalesay.ui

import androidx.lifecycle.ViewModel
import ca.hoogit.core.di.scopes.ActivityScoped
import ca.hoogit.core.di.viewmodel.ViewModelKey
import ca.hoogit.features.about.AboutFeatureModule
import ca.hoogit.features.translate.TranslateFeatureModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MainModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            TranslateFeatureModule::class,
            AboutFeatureModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity

    @Binds @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}