package com.example.bisnisproperti.ui.viewmodel.properti

import com.example.bisnisproperti.model.Properti

sealed class HomePropertiUiState {
    data class Success(
        val properti: List<Properti>
    ) : HomePropertiUiState()

    object Error : HomePropertiUiState()
    object Loading : HomePropertiUiState()
}