package com.example.chaikinweather.fragments.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chaikinweather.data.RemoteLocation
import com.example.chaikinweather.network.repository.WeatherDataRepository
import kotlinx.coroutines.launch

class LocationViewModel(private val weatherDataRepository: WeatherDataRepository) : ViewModel() {

    private val _searchResult = MutableLiveData<SearchResultDataState>()
    val searchResult: LiveData<SearchResultDataState> get() = _searchResult

    fun searchLocation(query: String) {
        viewModelScope.launch {
            emitSearchResultUiState(isLoading = true)
            val searchResult = weatherDataRepository.searchLocation(query)
            if (searchResult.isNullOrEmpty()) {
                emitSearchResultUiState(error = "No location found")
            } else {
                emitSearchResultUiState(locations = searchResult)
            }
        }
    }

    private fun emitSearchResultUiState(
        isLoading: Boolean = false,
        locations: List<RemoteLocation>? = null,
        error: String? = null
    ) {
        val SearchResultDataState = SearchResultDataState(isLoading, locations, error)
        _searchResult.value = SearchResultDataState
    }

    data class SearchResultDataState(
        val isLoading: Boolean,
        val locations: List<RemoteLocation>?,
        val error: String?
    )
}