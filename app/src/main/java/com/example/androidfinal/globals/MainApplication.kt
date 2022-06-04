package com.example.androidfinal.globals

import android.app.Application
import com.example.androidfinal.di.networkModule
import com.example.androidfinal.di.repositoryModule
import com.example.androidfinal.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(viewModelModule, repositoryModule, networkModule)
        }
    }

    companion object {
        lateinit var instance: MainApplication
    }
}