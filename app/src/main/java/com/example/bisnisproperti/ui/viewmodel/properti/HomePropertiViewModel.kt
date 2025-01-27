package com.example.bisnisproperti.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bisnisproperti.model.Properti
import com.example.bisnisproperti.repository.PropertiRepository

sealed class HomePropertiUiState {
    data class Success(
        val properti: List<Properti>
    ) : HomePropertiUiState()

    object Error : HomePropertiUiState()
    object Loading : HomePropertiUiState()
}

class HomePropertiViewModel(private val propertiRepository: PropertiRepository) : ViewModel() {
    var propertiUiState: HomePropertiUiState by mutableStateOf(HomePropertiUiState.Loading)
        private set
}