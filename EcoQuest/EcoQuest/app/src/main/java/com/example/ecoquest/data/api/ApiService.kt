package com.example.ecoquest.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private const val BASE_URL = "https://www.epa.gov/enviro/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: EnvirofactsApi = retrofit.create(EnvirofactsApi::class.java)
}
