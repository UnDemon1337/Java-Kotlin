package com.example.chaikinweather.dependency_injection

import com.example.chaikinweather.storage.SharedPreferencesManager
import org.koin.dsl.module

var StorageModule = module {
    single { SharedPreferencesManager(context = get(), gson = get()) }
}