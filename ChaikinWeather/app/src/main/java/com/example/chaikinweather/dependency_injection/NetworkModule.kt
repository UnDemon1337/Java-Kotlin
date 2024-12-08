package com.example.chaikinweather.dependency_injection

import com.example.chaikinweather.network.api.WeatherAPI
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {
    factory { okHttpClient()}
    single { retrofit(okHttpClient = get()) }
    factory { weatherAPI(retrofit = get()) }
}

private fun okHttpClient() = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .retryOnConnectionFailure(false)
    .build()

private fun retrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl("https://api.weatherapi.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun weatherAPI(retrofit: Retrofit) = retrofit.create(WeatherAPI::class.java)