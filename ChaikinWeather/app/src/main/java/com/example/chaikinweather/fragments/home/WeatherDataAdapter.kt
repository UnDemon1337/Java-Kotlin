package com.example.chaikinweather.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chaikinweather.data.CurrentLocation
import com.example.chaikinweather.data.CurrentWeather
import com.example.chaikinweather.data.WeatherData
import com.example.chaikinweather.databinding.ItemContainerCurrentLocationBinding
import com.example.chaikinweather.databinding.ItemContainerCurrentWeatherBinding
import coil.load
import com.example.chaikinweather.data.ForecastWeather
import com.example.chaikinweather.databinding.ItemContainerForecastBinding
import com.example.chaikinweather.storage.SharedPreferencesManager
import org.koin.java.KoinJavaComponent.inject
import java.util.Locale

class WeatherDataAdapter(
    private val onLocationClicked: () -> Unit,
    private val onSettingsClicked: () -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val INDEX_CURRENT_LOCATION = 0
        const val INDEX_CURRENT_WEATHER = 1
        const val INDEX_FORECAST = 2
    }

    private val weatherData = mutableListOf<WeatherData>()
    private val sharedPreferencesManager: SharedPreferencesManager by inject(SharedPreferencesManager::class.java)

    fun setCurrentLocation(currentLocation: CurrentLocation) {
        if (weatherData.isEmpty()) {
            weatherData.add(INDEX_CURRENT_LOCATION, currentLocation)
            notifyItemInserted(INDEX_CURRENT_LOCATION)
        } else {
            weatherData[INDEX_CURRENT_LOCATION] = currentLocation
            notifyItemChanged(INDEX_CURRENT_LOCATION)
        }
    }

    fun setCurrentWeather(currentWeather: CurrentWeather) {
        if (weatherData.getOrNull(INDEX_CURRENT_WEATHER) != null) {
            weatherData[INDEX_CURRENT_WEATHER] = currentWeather
            notifyItemChanged(INDEX_CURRENT_WEATHER)
        } else {
            weatherData.add(INDEX_CURRENT_WEATHER, currentWeather)
            notifyItemInserted(INDEX_CURRENT_WEATHER)
        }
    }

    fun setForecastWeather(forecastWeather: List<ForecastWeather>) {
        weatherData.removeAll{ it is ForecastWeather }
        notifyItemRangeRemoved(INDEX_FORECAST, weatherData.size)
        weatherData.addAll(INDEX_FORECAST, forecastWeather)
        notifyItemRangeChanged(INDEX_FORECAST, weatherData.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            INDEX_CURRENT_LOCATION -> CurrentLocationViewHolder(
                ItemContainerCurrentLocationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            INDEX_FORECAST -> ForecastWeatherViewHolder(
                ItemContainerForecastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> CurrentWeatherViewHolder(
                ItemContainerCurrentWeatherBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrentLocationViewHolder -> holder.bind(weatherData[position] as CurrentLocation)
            is CurrentWeatherViewHolder -> holder.bind(weatherData[position] as CurrentWeather)
            is ForecastWeatherViewHolder -> holder.bind(weatherData[position] as ForecastWeather)
        }
    }

    override fun getItemCount(): Int = weatherData.size

    inner class CurrentLocationViewHolder(
        private val binding: ItemContainerCurrentLocationBinding
    ): RecyclerView.ViewHolder (binding.root) {
        fun bind(currentLocation: CurrentLocation) {
            with(binding){
                textCurrentDate.text = currentLocation.data
                textCurrentLocation.text = currentLocation.location
                imageMyLocation.setOnClickListener { onLocationClicked() }
                imageMySettings.setOnClickListener { onSettingsClicked() }
                textCurrentLocation.setOnClickListener { onLocationClicked() }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(weatherData[position]) {
            is CurrentLocation -> INDEX_CURRENT_LOCATION
            is CurrentWeather -> INDEX_CURRENT_WEATHER
            is ForecastWeather -> INDEX_FORECAST
        }
    }

    inner class CurrentWeatherViewHolder(
        private val binding: ItemContainerCurrentWeatherBinding
    ): RecyclerView.ViewHolder (binding.root) {
        fun bind(currentWeather: CurrentWeather) {
            with(binding){
                imageCurrentWeather.load("https:${currentWeather.icon}"){
                    crossfade(true)
                }
                val tempUnit = sharedPreferencesManager.getTemperatureUnit()
                if (tempUnit == "Celsius (°C)") {
                    textTemperature.text = String.format("%s\u2103", currentWeather.temperature)
                } else {
                    val fahrenheitTemp = currentWeather.temperature * 9 / 5 + 32
                    val roundedFahrenheitTemp = String.format(Locale.getDefault(), "%.1f", fahrenheitTemp)
                    textTemperature.text = String.format("%s\u00B0F", roundedFahrenheitTemp)
                }
                textWind.text = String.format("%s km/h", currentWeather.wind)
                textHumidity.text = String.format("%s%%", currentWeather.humidity)
                textChanceOfRain.text = String.format("%s%%", currentWeather.chanceOfRain)
                textWindDirection.text = currentWeather.windDirection
                textSunset.text = currentWeather.sunset
                textSunrise.text = currentWeather.sunrise
            }
        }
    }

    inner class ForecastWeatherViewHolder(
        private val binding: ItemContainerForecastBinding
    ): RecyclerView.ViewHolder (binding.root){
        fun bind(forecastWeather: ForecastWeather) {
            with(binding){
                textTime.text = forecastWeather.time
                val tempUnit = sharedPreferencesManager.getTemperatureUnit()
                if (tempUnit == "Celsius (°C)") {
                    textTemperature.text = String.format("%s\u2103", forecastWeather.temperature)
                    textFeelsLike.text = String.format("%s\u2103", forecastWeather.feelsLike)
                } else {
                    val fahrenheitTemp = forecastWeather.temperature * 9 / 5 + 32
                    val fahrenheitFeelsLike = forecastWeather.feelsLike * 9 / 5 + 32
                    val roundedFahrenheitTemp = String.format(Locale.getDefault(), "%.1f", fahrenheitTemp)
                    val roundedFahrenheitFeelsLike = String.format(Locale.getDefault(), "%.1f", fahrenheitFeelsLike)
                    textTemperature.text = String.format("%s\u00B0F", roundedFahrenheitTemp)
                    textFeelsLike.text = String.format("%s\u00B0F", roundedFahrenheitFeelsLike)
                }
                imageIcon.load("https:${forecastWeather.icon}"){
                    crossfade(true)}
            }
        }
    }
}
