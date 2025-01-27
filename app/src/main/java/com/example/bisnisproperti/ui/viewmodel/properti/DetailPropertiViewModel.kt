package com.example.bisnisproperti.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.bisnisproperti.model.Properti
import com.example.bisnisproperti.repository.PropertiRepository

sealed class DetailPropertiUiState {
    data class Success(
        val properti: Properti
    ) : DetailPropertiUiState()

    object Error : DetailPropertiUiState()
    object Loading : DetailPropertiUiState()
}

class DetailPropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val propertiRepository: PropertiRepository
) : ViewModel() {
    var propertiUiState: DetailPropertiUiState by mutableStateOf(DetailPropertiUiState.Loading)
        private set

    private val idProperti: Int = checkNotNull(savedStateHandle["id_properti"])
}