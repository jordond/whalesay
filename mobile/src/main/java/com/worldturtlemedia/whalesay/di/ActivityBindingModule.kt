package com.worldturtlemedia.whalesay.di

import com.worldturtlemedia.whalesay.ui.MainModule
import dagger.Module

@Module(
    includes = [
        MainModule::class
    ]
)
internal abstract class ActivityBindingModule
