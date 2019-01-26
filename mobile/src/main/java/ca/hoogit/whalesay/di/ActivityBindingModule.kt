package ca.hoogit.whalesay.di

import ca.hoogit.whalesay.ui.MainModule
import dagger.Module

@Module(
    includes = [
        MainModule::class
    ]
)
internal abstract class ActivityBindingModule