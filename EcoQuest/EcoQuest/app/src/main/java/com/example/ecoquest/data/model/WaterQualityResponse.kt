package com.example.ecoquest.data.model

data class WaterQualityResponse(
    val waterData: List<WaterData>
)

data class WaterData(
    val stationName: String,
    val state: String,
    val county: String,
    val waterQuality: String,
    val lastUpdated: String
)
