package com.example.bisnisproperti.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.bisnisproperti.repository.PropertiRepository

class UpdatePropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val propertiRepository: PropertiRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertPropertiUiState())
        private set

    private val idProperti: Int = checkNotNull(savedStateHandle["id_properti"])
}