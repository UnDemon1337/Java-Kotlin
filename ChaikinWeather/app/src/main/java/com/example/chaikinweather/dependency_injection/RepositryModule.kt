package com.example.chaikinweather.dependency_injection

import com.example.chaikinweather.network.repository.WeatherDataRepository
import org.koin.dsl.module

val RepositryModule = module {
    single { WeatherDataRepository(weatherAPI = get()) }
}