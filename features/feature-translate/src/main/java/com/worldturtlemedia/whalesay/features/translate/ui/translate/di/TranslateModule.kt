package com.worldturtlemedia.whalesay.features.translate.ui.translate.di

import androidx.lifecycle.ViewModel
import com.worldturtlemedia.whalesay.core.di.scopes.FragmentScoped
import com.worldturtlemedia.whalesay.core.di.viewmodel.ViewModelKey
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateFragment
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class TranslateModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeTranslateFragment(): TranslateFragment

    @Binds @IntoMap
    @ViewModelKey(TranslateViewModel::class)
    abstract fun bindTranslateViewModel(viewModel: TranslateViewModel): ViewModel
}
