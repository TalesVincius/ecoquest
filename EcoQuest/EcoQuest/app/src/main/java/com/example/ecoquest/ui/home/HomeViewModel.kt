package com.example.ecoquest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecoquest.data.model.WaterQualityResponse
import com.example.ecoquest.data.repository.ChallengeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ChallengeRepository = ChallengeRepository()
) : ViewModel() {

    private val _waterQualityData =
        MutableStateFlow<WaterQualityResponse?>(null)

    val waterQualityData: StateFlow<WaterQualityResponse?> =
        _waterQualityData

    fun fetchWaterQualityData(state: String, county: String) {
        viewModelScope.launch {
            _waterQualityData.value =
                repository.getWaterQualityData(state, county)
        }
    }
}
