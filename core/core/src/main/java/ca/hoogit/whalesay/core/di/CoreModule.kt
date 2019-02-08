package ca.hoogit.whalesay.core.di

import ca.hoogit.whalesay.core.coroutines.CoroutinesDispatcherProviderModule
import ca.hoogit.whalesay.core.di.viewmodel.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        CoroutinesDispatcherProviderModule::class
    ]
)
abstract class CoreModule
