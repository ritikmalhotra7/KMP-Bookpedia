package com.sample.bookpedia.core.presentation

import android.app.Application
import com.sample.bookpedia.core.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@Application)
        }
    }
}