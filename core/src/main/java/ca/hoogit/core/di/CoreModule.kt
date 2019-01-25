package ca.hoogit.core.di

import ca.hoogit.core.coroutines.CoroutinesDispatcherProviderModule
import ca.hoogit.core.di.viewmodel.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        CoroutinesDispatcherProviderModule::class
    ]
)
abstract class CoreModule