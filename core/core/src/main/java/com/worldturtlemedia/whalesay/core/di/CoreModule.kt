package com.worldturtlemedia.whalesay.core.di

import com.worldturtlemedia.whalesay.core.coroutines.CoroutinesDispatcherProviderModule
import com.worldturtlemedia.whalesay.core.di.viewmodel.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        CoroutinesDispatcherProviderModule::class
    ]
)
abstract class CoreModule
