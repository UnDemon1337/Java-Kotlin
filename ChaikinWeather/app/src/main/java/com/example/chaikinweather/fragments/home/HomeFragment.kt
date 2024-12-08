package com.example.chaikinweather.fragments.home

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.chaikinweather.R
import com.example.chaikinweather.data.CurrentLocation
import com.example.chaikinweather.databinding.FragmentHomeBinding
import com.example.chaikinweather.storage.SharedPreferencesManager
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        const val REQUEST_KEY_MANUAL_LOCATION_SEARCH = "manual_location_search"
        const val KEY_LOCATION_TEXT = "location_text"
        const val KEY_LATITUDE = "latitude"
        const val KEY_LONGITUDE = "longitude"

    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val homeViewModel: HomeViewModel by viewModel()
    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }
    private val geocoder by lazy { Geocoder(requireContext()) }

    private val weatherDataAdapter = WeatherDataAdapter(
        onLocationClicked = { showLocationOptions()},
        onSettingsClicked = { findNavController().navigate(R.id.action_home_to_settings) }
    )

    private val sharedPreferencesManager: SharedPreferencesManager by inject()

    private val locationpermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isGranted ->
        if (isGranted) getCurrentLocation()
        else Toast.makeText(requireContext(), "Permission not granted", Toast.LENGTH_SHORT).show()
    }

    private var isInitialLocationSet: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val currentTheme = sharedPreferencesManager.getAppTheme()
        applyTheme(currentTheme)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isLocationPermissionGranted()) requestLocationPermission()
        setWeatherDataAdapter()
        setCurrentLocation(currentLocation = sharedPreferencesManager.getCurrentLocation())
        setObservers()
        setListeners()
        if(!isInitialLocationSet) {
            setCurrentLocation(currentLocation = sharedPreferencesManager.getCurrentLocation())
        }
    }

    private fun setListeners() {
        binding.SwipeRefreshLayout.setOnRefreshListener {
            setCurrentLocation(currentLocation = sharedPreferencesManager.getCurrentLocation())
        }
    }

    private fun setObservers() {
        with(homeViewModel){
            currentLocation.observe(viewLifecycleOwner) {
                val currentLocationDataState = it.getContentIfNotHandled() ?: return@observe
                if (currentLocationDataState.isLoading) {
                    showLoading()
                }
                currentLocationDataState.currentLocation?.let { currentLocation ->
                    hideLoading()
                    sharedPreferencesManager.saveCurrentLocation(currentLocation)
                    setCurrentLocation(currentLocation)
                }
                currentLocationDataState.error?.let { error ->
                    hideLoading()
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                }
            }
            weatherData.observe(viewLifecycleOwner) {
                val weatherDataState = it.getContentIfNotHandled() ?: return@observe
                binding.SwipeRefreshLayout.isRefreshing = weatherDataState.isLoading
                weatherDataState.currentWeather?.let { currentWeather ->
                    weatherDataAdapter.setCurrentWeather(currentWeather)
                }
                weatherDataState.forecast?.let { forecasts ->
                    weatherDataAdapter.setForecastWeather(forecasts)
                }
                weatherDataState.error?.let { error ->
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setWeatherDataAdapter() {
        binding.RecyclerView.itemAnimator = null
        binding.RecyclerView.adapter = weatherDataAdapter
    }

    private fun setCurrentLocation(currentLocation: CurrentLocation? = null) {
        weatherDataAdapter.setCurrentLocation(currentLocation ?: CurrentLocation())
        currentLocation?.let { getWeatherData(currentLocation = it) }
    }

    private fun getCurrentLocation() {
        homeViewModel.getCurrentLocation(fusedLocationProviderClient, geocoder)
    }

    private fun settingsLocationPermissionState(): Boolean {
        sharedPreferencesManager.getLocationPermissionState()
        val isLocationPermissionEnabled = sharedPreferencesManager.getLocationPermissionState()
        Log.d("HomeFragment", "isLocationPermissionEnabled: $isLocationPermissionEnabled")
        return isLocationPermissionEnabled
    }

    private fun isLocationPermissionGranted(): Boolean {
        val isLocationPermissionGranted = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED

        return isLocationPermissionGranted
    }

    private fun requestLocationPermission() {
        locationpermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun proceedWithCurrentLocation() {
        if (isLocationPermissionGranted() && settingsLocationPermissionState()) getCurrentLocation()
        else Toast.makeText(requireContext(), "Location permission not granted", Toast.LENGTH_SHORT).show()

    }

    private fun showLocationOptions() {
        val options = arrayOf("Current Location", "Search Location")
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Choose method")
            setItems(options) { _, which ->
                when (which) {
                    0 -> proceedWithCurrentLocation()
                    1 -> startManualLocationSearch()
                }
            }
            show()
        }
    }

    private fun showLoading() {
        with(binding) {
            RecyclerView.visibility = View.GONE
            SwipeRefreshLayout.isEnabled = false
            SwipeRefreshLayout.isRefreshing = true
        }
    }

    private fun hideLoading() {
        with(binding) {
            RecyclerView.visibility = View.VISIBLE
            SwipeRefreshLayout.isEnabled = true
            SwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun startManualLocationSearch() {
        startListeningManualLocationSearch()
        findNavController().navigate(R.id.action_home_to_location)
    }

    private fun startListeningManualLocationSearch() {
        setFragmentResultListener(REQUEST_KEY_MANUAL_LOCATION_SEARCH) { _, bundle ->
            stopListeningManualLocationSearch()
            val currentLocation = CurrentLocation(
                location = bundle.getString(KEY_LOCATION_TEXT) ?: "N/A",
                latitude = bundle.getDouble(KEY_LATITUDE),
                longitude = bundle.getDouble(KEY_LONGITUDE)
            )
            sharedPreferencesManager.saveCurrentLocation(currentLocation)
            setCurrentLocation(currentLocation)
        }
    }

    private fun stopListeningManualLocationSearch() {
        clearFragmentResultListener(REQUEST_KEY_MANUAL_LOCATION_SEARCH)
    }

    private fun getWeatherData(currentLocation: CurrentLocation) {
        if(currentLocation.latitude != null && currentLocation.longitude != null) {
            homeViewModel.getWeatherData(
                latitude = currentLocation.latitude,
                longitude = currentLocation.longitude
            )
        }
    }

    private fun applyTheme(appTheme: SharedPreferencesManager.AppTheme) {
        when (appTheme) {
            SharedPreferencesManager.AppTheme.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            SharedPreferencesManager.AppTheme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            SharedPreferencesManager.AppTheme.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}