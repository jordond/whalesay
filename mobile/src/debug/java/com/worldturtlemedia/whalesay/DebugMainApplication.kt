package com.worldturtlemedia.whalesay

import com.etiennelenhart.eiffel.Eiffel
import com.etiennelenhart.eiffel.logger.logger
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class DebugMainApplication : MainApplication() {

    override fun onCreate() {
        super.onCreate()

        setupLeakCanary()
        setupStetho()
    }

    override fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    override fun setupEiffel() {
        Eiffel.debugMode(true, logger { priority, tag, message ->
            Timber.tag("Eiffel:$tag").log(priority, message)
        })
    }

    private fun setupLeakCanary() {
        LeakCanary.install(this)
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }
}
