package com.example.chaikinweather.dependency_injection

import com.google.gson.Gson
import org.koin.dsl.module

var SerializerModule = module {
    single { Gson() }
}