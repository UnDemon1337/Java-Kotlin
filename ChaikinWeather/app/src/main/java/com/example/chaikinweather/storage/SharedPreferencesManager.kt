package com.example.chaikinweather.storage

import com.google.gson.Gson
import com.example.chaikinweather.data.CurrentLocation

class SharedPreferencesManager(context : android.content.Context, private val gson: Gson) {

    private companion object{
        const val PREF_NAME = "WeatherApp"
        const val KEY_CURRENT_LOCATION = "current_location"
        const val KEY_TEMP_UNIT = "temperature_unit"
        const val KEY_THEME = "theme"
    }

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, android.content.Context.MODE_PRIVATE)

    fun saveCurrentLocation(currentLocation: CurrentLocation) {
        val json = gson.toJson(currentLocation)
        sharedPreferences.edit().putString(KEY_CURRENT_LOCATION, json).apply()
    }

    fun getCurrentLocation(): CurrentLocation? {
        return sharedPreferences.getString(
            KEY_CURRENT_LOCATION,
            null
        )?.let { json ->
            gson.fromJson(json, CurrentLocation::class.java)
        }
    }

    fun saveTemperatureUnit(unit: String) {
        sharedPreferences.edit().putString(KEY_TEMP_UNIT, unit).apply()
    }

    fun getTemperatureUnit(): String? {
        return sharedPreferences.getString(KEY_TEMP_UNIT, "Celsius")
    }

    fun savePushNotificationsState(isEnabled: Boolean) {
        sharedPreferences.edit().putBoolean("PUSH_NOTIFICATIONS_STATE", isEnabled).apply()
    }

    fun getPushNotificationsState(): Boolean {
        return sharedPreferences.getBoolean("PUSH_NOTIFICATIONS_STATE", false)
    }

    enum class AppTheme(val value: String) {
        SYSTEM("system"), LIGHT("light"), DARK("dark")
    }

    fun saveAppTheme(theme: AppTheme) {
        sharedPreferences.edit().putString(KEY_THEME, theme.value).apply()
    }

    fun getAppTheme(): AppTheme {
        val themeValue = sharedPreferences.getString(KEY_THEME, AppTheme.SYSTEM.value)
        return AppTheme.entries.firstOrNull { it.value == themeValue } ?: AppTheme.SYSTEM
    }

    fun saveLocationPermissionState(isEnabled: Boolean) {
        sharedPreferences.edit().putBoolean("location_permission", isEnabled).apply()
    }

    fun getLocationPermissionState(): Boolean {
        return sharedPreferences.getBoolean("location_permission", false)
    }
}
