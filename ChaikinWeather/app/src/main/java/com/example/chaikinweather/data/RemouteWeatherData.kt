package com.example.chaikinweather.data

import com.google.gson.annotations.SerializedName

data class RemoteWeatherData(
    val current: CurrentWeatherRemote,
    val forecast: ForecastRemote
)


data class CurrentWeatherRemote(
    @SerializedName("temp_c") val temperature: Float,
    val condition: WeatherConditionRemote,
    @SerializedName("wind_kph") val wind: Float,
    val humidity: Int,
    @SerializedName("wind_dir") val windDirection: String,
)


data class ForecastRemote(
    @SerializedName("forecastday") val forecastDay: List<ForecastDataRemote>
)


data class ForecastDataRemote(
    val day: DayRemote,
    val astro: AstroRemote,
    val hour: List<ForecastHourRemote>
)

data class AstroRemote(
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String,
)

data class DayRemote(
    @SerializedName("dayly_chance_of_rain") val chanceOfRain: Int
)


data class WeatherConditionRemote(
    val icon: String
)


data class ForecastHourRemote(
    val time: String,
    @SerializedName("temp_c") val temperature: Float,
    @SerializedName("feelslike_c") val feelsLike: Float,
    val condition: WeatherConditionRemote
)
