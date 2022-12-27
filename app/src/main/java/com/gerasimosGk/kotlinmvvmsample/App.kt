package com.gerasimosGk.kotlinmvvmsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Logging only in Debug variant
        Timber.plant(Timber.DebugTree())
    }
}