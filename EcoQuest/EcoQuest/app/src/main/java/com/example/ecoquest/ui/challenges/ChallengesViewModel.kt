// com/example/ecoquest/ui/challenges/ChallengesViewModel.kt
package com.example.ecoquest.ui.challenges

import androidx.lifecycle.ViewModel
import com.example.ecoquest.data.model.Challenge
import com.example.ecoquest.data.model.ChallengeCategory
import com.example.ecoquest.data.model.ChallengeDifficulty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChallengesViewModel : ViewModel() {

    private val _challenges = MutableStateFlow<List<Challenge>>(emptyList())
    val challenges: StateFlow<List<Challenge>> = _challenges

    init {
        _challenges.value = listOf(
            // ÁGUA
            Challenge(
                id = 1,
                title = "Reduzir banho para 5 min",
                description = "Economize água tomando banhos mais curtos.",
                category = ChallengeCategory.WATER,
                difficulty = ChallengeDifficulty.EASY,
                points = 20
            ),
            Challenge(
                id = 2,
                title = "Fechar a torneira ao escovar os dentes",
                description = "Evite deixar a água correndo à toa.",
                category = ChallengeCategory.WATER,
                difficulty = ChallengeDifficulty.EASY,
                points = 10
            ),
            Challenge(
                id = 3,
                title = "Reaproveitar água da máquina de lavar",
                description = "Use a água para lavar o chão ou o quintal.",
                category = ChallengeCategory.WATER,
                difficulty = ChallengeDifficulty.MEDIUM,
                points = 25
            ),
            Challenge(
                id = 4,
                title = "Verificar possíveis vazamentos",
                description = "Olhe torneiras e descargas que possam estar pingando.",
                category = ChallengeCategory.WATER,
                difficulty = ChallengeDifficulty.MEDIUM,
                points = 30
            ),

            // PLÁSTICO
            Challenge(
                id = 5,
                title = "Usar garrafa reutilizável",
                description = "Evite garrafas plásticas descartáveis no dia de hoje.",
                category = ChallengeCategory.PLASTIC,
                difficulty = ChallengeDifficulty.EASY,
                points = 15
            ),
            Challenge(
                id = 6,
                title = "Recusar sacolas plásticas",
                description = "Leve sua ecobag ou sacola retornável.",
                category = ChallengeCategory.PLASTIC,
                difficulty = ChallengeDifficulty.EASY,
                points = 15
            ),
            Challenge(
                id = 7,
                title = "Separar plástico para reciclagem",
                description = "Separe as embalagens plásticas do lixo comum.",
                category = ChallengeCategory.PLASTIC,
                difficulty = ChallengeDifficulty.MEDIUM,
                points = 25
            ),
            Challenge(
                id = 8,
                title = "Um dia sem descartar plástico",
                description = "Tente passar o dia sem jogar plástico no lixo comum.",
                category = ChallengeCategory.PLASTIC,
                difficulty = ChallengeDifficulty.HARD,
                points = 40
            )
        )
    }

    fun toggleChallenge(id: Int) {
        _challenges.value = _challenges.value.map { challenge ->
            if (challenge.id == id) {
                challenge.copy(isCompleted = !challenge.isCompleted)
            } else challenge
        }
    }

    val totalPoints: Int
        get() = _challenges.value.filter { it.isCompleted }.sumOf { it.points }

    val completedCount: Int
        get() = _challenges.value.count { it.isCompleted }
}
