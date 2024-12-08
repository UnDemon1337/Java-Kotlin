package com.example.chaikinweather.network.api

import com.example.chaikinweather.data.RemoteLocation
import com.example.chaikinweather.data.RemoteWeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherAPI {

    companion object {
        const val BASE_URL = "http://api.weatherapi.com/v1/"
        const val API_KEY = "5a035132b34b46e68f4175512240512"
    }

    @GET("search.json")
    suspend fun searchLocation(
        @Query("key") key: String = API_KEY,
        @Query("q") query: String
    ): Response<List<RemoteLocation>>

    @GET("forecast.json")
    suspend fun getWeatherData(
        @Query("key") key: String = API_KEY,
        @Query("q") query: String
    ): Response<RemoteWeatherData>
}