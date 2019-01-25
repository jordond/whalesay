package ca.hoogit.whalesay.di

import androidx.lifecycle.ViewModel
import ca.hoogit.core.di.scopes.ActivityScoped
import ca.hoogit.core.di.viewmodel.ViewModelKey
import ca.hoogit.whalesay.ui.MainActivity
import ca.hoogit.whalesay.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @Binds @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}