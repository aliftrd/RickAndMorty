package com.takehomechallenge.aliftrd.base

import android.app.Application
import com.takehomechallenge.aliftrd.di.databaseModule
import com.takehomechallenge.aliftrd.di.feature.characterModule
import com.takehomechallenge.aliftrd.di.networkModule
import com.takehomechallenge.aliftrd.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(listOf(
                characterModule,
                networkModule,
                databaseModule,
                viewModelModule
            ))
        }
    }
}