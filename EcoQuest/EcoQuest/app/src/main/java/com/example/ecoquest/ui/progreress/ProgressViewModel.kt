package com.example.ecoquest.ui.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecoquest.utils.PointsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ProgressUiState(
    val totalPoints: Int = 0,
    val goalPoints: Int = 100,          // 100 pontos = barra cheia
    val medal: String = "",
    val tip: String = ""
)

class ProgressViewModel(
    private val pointsManager: PointsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProgressUiState())
    val uiState: StateFlow<ProgressUiState> = _uiState.asStateFlow()

    init {
        refreshProgress()
    }

    fun refreshProgress() {
        val points = pointsManager.getTotalPoints()

        val medal = when {
            points >= 100 -> "Guardião da Natureza"
            points >= 60  -> "Anti-Plástico"
            points >= 30  -> "Guerreiro da Água"
            points > 0    -> "Iniciante Ecológico"
            else          -> "Nenhuma medalha ainda"
        }

        val tip = when {
            points == 0 -> "Comece com um desafio bem fácil hoje, como fechar a torneira ao escovar os dentes."
            points < 30 -> "Foque em desafios de água para subir de nível rapidinho."
            points < 60 -> "Agora é a hora de reduzir plástico: recuse copos e talheres descartáveis."
            else        -> "Você já está mandando muito bem! Que tal chamar alguém da família para fazer desafios junto?"
        }

        _uiState.value = ProgressUiState(
            totalPoints = points,
            goalPoints = 100,
            medal = medal,
            tip = tip
        )
    }
}

class ProgressViewModelFactory(
    private val pointsManager: PointsManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProgressViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProgressViewModel(pointsManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
