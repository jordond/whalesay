package ca.hoogit.features.about.ui.di

import androidx.lifecycle.ViewModel
import ca.hoogit.whalesay.core.di.scopes.FragmentScoped
import ca.hoogit.whalesay.core.di.viewmodel.ViewModelKey
import ca.hoogit.features.about.ui.AboutFragment
import ca.hoogit.features.about.ui.AboutViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class AboutModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeAboutFragment(): AboutFragment

    @Binds @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutViewModel(viewModel: AboutViewModel): ViewModel
}