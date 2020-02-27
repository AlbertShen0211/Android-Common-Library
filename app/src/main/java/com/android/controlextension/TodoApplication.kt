package com.android.controlextension

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

open class TodoApplication : Application() {
        override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}
