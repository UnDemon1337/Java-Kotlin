package com.example.chaikinweather.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.chaikinweather.R
import com.example.chaikinweather.databinding.FragmentSettingsBinding
import com.example.chaikinweather.storage.SharedPreferencesManager
import com.example.chaikinweather.utils.scheduleMorningNotification
import org.koin.android.ext.android.inject

class SettingsFragment : Fragment() {

    private val sharedPreferencesManager: SharedPreferencesManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.imageClose.setOnClickListener { findNavController().popBackStack() }

        val switchLocationPermission: SwitchCompat = binding.switchLocationPermission
        switchLocationPermission.isChecked = sharedPreferencesManager.getLocationPermissionState()

        switchLocationPermission.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferencesManager.saveLocationPermissionState(isChecked)
        }

        val switchPushNotifications: SwitchCompat = binding.switchPushNotifications
        switchPushNotifications.isChecked = sharedPreferencesManager.getPushNotificationsState()

        switchPushNotifications.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferencesManager.savePushNotificationsState(isChecked)

            if (isChecked) {
                requireContext().scheduleMorningNotification()
            }
        }

        val temperatureUnits = resources.getStringArray(R.array.temperature_units)
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.temperature_units,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTemperatureUnit.adapter = adapter

        val selectedUnit = sharedPreferencesManager.getTemperatureUnit() ?: "Celsius"
        val selectedPosition = temperatureUnits.indexOf(selectedUnit)
        binding.spinnerTemperatureUnit.setSelection(selectedPosition)

        binding.spinnerTemperatureUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = temperatureUnits[position]
                sharedPreferencesManager.saveTemperatureUnit(selectedItem)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }

        val themeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.theme_options,
            android.R.layout.simple_spinner_item
        )
        themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTheme.adapter = themeAdapter

        val currentTheme = sharedPreferencesManager.getAppTheme()
        val themePosition = SharedPreferencesManager.AppTheme.entries.indexOf(currentTheme)
        binding.spinnerTheme.setSelection(themePosition)

        binding.spinnerTheme.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedTheme = SharedPreferencesManager.AppTheme.entries[position]
                sharedPreferencesManager.saveAppTheme(selectedTheme)

                applyTheme(selectedTheme)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        return binding.root
    }
    private fun applyTheme(theme: SharedPreferencesManager.AppTheme) {
        when (theme) {
            SharedPreferencesManager.AppTheme.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            SharedPreferencesManager.AppTheme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            SharedPreferencesManager.AppTheme.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}