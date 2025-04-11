package com.example.storeapp

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.framework.di.frameWorkModule
import com.example.storeapp.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StoreApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@StoreApp)
            modules(domainModule, dataModule, frameWorkModule, uiModule)
        }
    }
}