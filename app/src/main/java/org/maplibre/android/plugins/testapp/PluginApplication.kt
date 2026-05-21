package com.mapvina.android.plugins.testapp

import android.app.Application
import com.mapvina.android.MapVina
import timber.log.Timber

class PluginApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeLogger()
        MapVina.getInstance(this)
    }

    private fun initializeLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
