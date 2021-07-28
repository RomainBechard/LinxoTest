package com.romainbechard.linxotest

import android.app.Application
import timber.log.Timber

class LinxoTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}