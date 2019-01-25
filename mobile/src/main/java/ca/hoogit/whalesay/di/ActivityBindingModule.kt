package ca.hoogit.whalesay.di

import ca.hoogit.core.di.scopes.ActivityScoped
import ca.hoogit.whalesay.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}