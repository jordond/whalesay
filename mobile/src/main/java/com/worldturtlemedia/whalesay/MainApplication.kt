package com.worldturtlemedia.whalesay

import androidx.annotation.CallSuper
import com.worldturtlemedia.whalesay.di.DaggerMobileComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class MainApplication : DaggerApplication() {

    @CallSuper
    override fun onCreate() {
        super.onCreate()

        setupTimber()
        setupEiffel()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMobileComponent.builder().create(this)
    }

    open fun setupTimber() {}

    open fun setupEiffel() {}
}
