package org.jihye.acnhhb

import android.app.Application
import org.jihye.acnhhb.di.startInit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startInit {
            androidLogger()
            androidContext(this@MainApplication)
        }
    }
}
