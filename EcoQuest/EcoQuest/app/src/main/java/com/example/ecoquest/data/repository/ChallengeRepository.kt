package com.example.ecoquest.data.repository

import com.example.ecoquest.data.model.Challenge
import com.example.ecoquest.data.model.ChallengeCategory
import com.example.ecoquest.data.model.ChallengeDifficulty
import com.example.ecoquest.data.model.WaterData
import com.example.ecoquest.data.model.WaterQualityResponse
import kotlinx.coroutines.delay

class ChallengeRepository {

    // Lista "fixa" só pra projeto da faculdade
    private val mockChallenges = listOf(
        Challenge(
            id = 1,
            title = "Banho mais rápido",
            description = "Tente reduzir seu banho em 5 minutos hoje.",
            category = ChallengeCategory.WATER,
            difficulty = ChallengeDifficulty.EASY,
            points = 10
        ),
        Challenge(
            id = 2,
            title = "Fechar a torneira",
            description = "Feche a torneira enquanto escova os dentes.",
            category = ChallengeCategory.WATER,
            difficulty = ChallengeDifficulty.EASY,
            points = 8
        ),
        Challenge(
            id = 3,
            title = "Reutilizar garrafa",
            description = "Use uma garrafa reutilizável em vez de comprar água em plástico.",
            category = ChallengeCategory.PLASTIC,
            difficulty = ChallengeDifficulty.MEDIUM,
            points = 15
        ),
        Challenge(
            id = 4,
            title = "Evitar descartáveis",
            description = "Passe o dia sem usar copos ou talheres descartáveis.",
            category = ChallengeCategory.PLASTIC,
            difficulty = ChallengeDifficulty.HARD,
            points = 25
        )
    )

    // Simula uma chamada de API para desafios
    suspend fun getChallenges(): List<Challenge> {
        delay(500) // só para mostrar "carregando" se quiser
        return mockChallenges
    }

    // 👉 "faça a 2": simula uma API de qualidade da água
    suspend fun getWaterQualityData(
        state: String,
        county: String
    ): WaterQualityResponse {
        delay(500) // simula rede

        val mock = listOf(
            WaterData(
                stationName = "Estação Central",
                state = state,
                county = county,
                waterQuality = "Boa",
                lastUpdated = "2025-12-07"
            ),
            WaterData(
                stationName = "Estação Secundária",
                state = state,
                county = county,
                waterQuality = "Regular",
                lastUpdated = "2025-12-06"
            )
        )

        return WaterQualityResponse(waterData = mock)
    }
}
