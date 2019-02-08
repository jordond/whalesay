package com.worldturtlemedia.whalesay.features.translate.ui.error.di

import androidx.lifecycle.ViewModel
import com.worldturtlemedia.whalesay.core.di.scopes.FragmentScoped
import com.worldturtlemedia.whalesay.core.di.viewmodel.ViewModelKey
import com.worldturtlemedia.whalesay.features.translate.ui.error.ErrorFragment
import com.worldturtlemedia.whalesay.features.translate.ui.error.ErrorViewModel
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
