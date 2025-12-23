package org.jihye.acnhhb

import android.app.Application
import org.jihye.acnhhb.di.startDependencyInjection
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startDependencyInjection {
            androidLogger()
            androidContext(this@MainApplication)
        }
    }
}
