package com.example.todotdd

import android.app.Application
import com.example.todotdd.di.appModle
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // TODO koin trigger
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(appModle)
        }

    }
}