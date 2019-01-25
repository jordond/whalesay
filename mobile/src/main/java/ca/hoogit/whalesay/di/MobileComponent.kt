package ca.hoogit.whalesay.di

import ca.hoogit.core.di.CoreModule
import ca.hoogit.whalesay.MainApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        CoreModule::class,
        ActivityBindingModule::class
    ]
)
internal interface MobileComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}