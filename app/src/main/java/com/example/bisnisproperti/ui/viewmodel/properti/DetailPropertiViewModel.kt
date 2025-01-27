package com.example.bisnisproperti.ui.viewmodel.properti

import com.example.bisnisproperti.model.Properti

sealed class DetailPropertiUiState {
    data class Success(
        val properti: Properti
    ) : DetailPropertiUiState()

    object Error : DetailPropertiUiState()
    object Loading : DetailPropertiUiState()
}