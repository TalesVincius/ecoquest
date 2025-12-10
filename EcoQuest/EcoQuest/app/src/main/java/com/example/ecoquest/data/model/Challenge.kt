package com.example.ecoquest.data.model

// Categorias focadas em água e plástico
enum class ChallengeCategory(val displayName: String) {
    WATER("Água"),
    PLASTIC("Plástico")
}

// Dificuldade dos desafios
enum class ChallengeDifficulty(val displayName: String) {
    EASY("Fácil"),
    MEDIUM("Médio"),
    HARD("Difícil")
}

// Modelo único de desafio para o app todo
data class Challenge(
    val id: Int,
    val title: String,
    val description: String,
    val category: ChallengeCategory,
    val difficulty: ChallengeDifficulty,
    val points: Int,
    val isCompleted: Boolean = false
)
