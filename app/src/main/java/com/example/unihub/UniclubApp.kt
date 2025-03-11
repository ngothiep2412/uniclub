package com.example.unihub

import android.app.Application
import com.example.unihub.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UniclubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@UniclubApp)
            modules(appModule)
        }
    }
}