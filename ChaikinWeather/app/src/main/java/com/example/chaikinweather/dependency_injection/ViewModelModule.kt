package com.example.chaikinweather.dependency_injection

import com.example.chaikinweather.fragments.home.HomeViewModel
import com.example.chaikinweather.fragments.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { HomeViewModel(weatherDataRepository = get()) }
    viewModel { LocationViewModel(weatherDataRepository = get()) }
}
