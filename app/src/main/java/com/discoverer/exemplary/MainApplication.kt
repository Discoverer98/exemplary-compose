package com.discoverer.exemplary

import android.app.Application
import com.discoverer.exemplary.injection.appModule
import com.discoverer.exemplary.injection.repoModule
import com.discoverer.exemplary.injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }

} // MainApplication class