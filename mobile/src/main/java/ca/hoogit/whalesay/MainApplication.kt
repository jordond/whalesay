package ca.hoogit.whalesay

import ca.hoogit.whalesay.di.DaggerMobileComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMobileComponent.builder().create(this)
    }
}
