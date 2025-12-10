package com.example.ecoquest.data.api

import com.example.ecoquest.data.model.WaterQualityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EnvirofactsApi {

    @GET("water_quality")
    suspend fun getWaterQualityData(
        @Query("state") state: String,
        @Query("county") county: String
    ): WaterQualityResponse
}
