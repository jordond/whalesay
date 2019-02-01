package ca.hoogit.features.translate.ui.error.di

import androidx.lifecycle.ViewModel
import ca.hoogit.whalesay.core.di.scopes.FragmentScoped
import ca.hoogit.whalesay.core.di.viewmodel.ViewModelKey
import ca.hoogit.features.translate.ui.error.ErrorFragment
import ca.hoogit.features.translate.ui.error.ErrorViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ErrorModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeErrorFragment(): ErrorFragment

    @Binds @IntoMap
    @ViewModelKey(ErrorViewModel::class)
    abstract fun bindErrorViewModel(viewModel: ErrorViewModel): ViewModel
}