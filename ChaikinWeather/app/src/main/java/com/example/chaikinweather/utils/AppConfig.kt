package com.example.chaikinweather.utils

import android.app.Application
import com.example.chaikinweather.dependency_injection.NetworkModule
import com.example.chaikinweather.dependency_injection.ViewModelModule
import com.example.chaikinweather.dependency_injection.RepositryModule
import com.example.chaikinweather.dependency_injection.SerializerModule
import com.example.chaikinweather.dependency_injection.StorageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppConfig: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppConfig)
            modules(
                listOf(
                    ViewModelModule,
                    RepositryModule,
                    SerializerModule,
                    StorageModule,
                    NetworkModule
                )
            )
        }
    }

}