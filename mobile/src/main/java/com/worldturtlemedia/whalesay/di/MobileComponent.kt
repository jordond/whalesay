package com.worldturtlemedia.whalesay.di

import com.worldturtlemedia.data.fs.FSModule
import com.worldturtlemedia.whalesay.MainApplication
import com.worldturtlemedia.whalesay.core.di.CoreModule
import com.worldturtlemedia.whalesay.data.api.APIModule
import com.worldturtlemedia.whalesay.data.db.DatabaseModule
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
        DatabaseModule::class,
        APIModule::class,
        FSModule::class,
        MobileModule::class,
        ActivityBindingModule::class
    ]
)
internal interface MobileComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}
