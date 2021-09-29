package com.discoverer.exemplary

import android.app.Application
import com.discoverer.exemplary.di.module.appModule
import com.discoverer.exemplary.di.module.repoModule
import com.discoverer.exemplary.di.module.viewModelModule
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