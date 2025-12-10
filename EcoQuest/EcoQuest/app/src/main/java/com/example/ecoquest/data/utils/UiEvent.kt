package com.example.ecoquest.utils

// Eventos que acontecem uma vez só (toast, navegação, etc.)
sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
    object NavigateBack : UiEvent()
}
