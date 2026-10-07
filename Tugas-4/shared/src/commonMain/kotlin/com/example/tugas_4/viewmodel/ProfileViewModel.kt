package com.example.tugas_4.viewmodel

import com.example.tugas_4.data.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun updateProfile(newName: String, newBio: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = newName,
                bio = newBio,
                isEditing = false
            )
        }
    }

    fun setEditing(isEditing: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isEditing = isEditing)
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isDarkMode = enabled)
        }
    }
}