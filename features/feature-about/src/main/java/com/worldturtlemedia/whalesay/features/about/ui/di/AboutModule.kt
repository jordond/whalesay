package com.worldturtlemedia.whalesay.features.about.ui.di

import androidx.lifecycle.ViewModel
import com.worldturtlemedia.whalesay.core.di.scopes.FragmentScoped
import com.worldturtlemedia.whalesay.core.di.viewmodel.ViewModelKey
import com.worldturtlemedia.whalesay.features.about.ui.AboutFragment
import com.worldturtlemedia.whalesay.features.about.ui.AboutViewModel
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
