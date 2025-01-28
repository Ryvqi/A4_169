package com.example.bisnisproperti.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.JenisProperti
import com.example.bisnisproperti.repository.JenisPropertiRepository
import kotlinx.coroutines.launch

class InsertJenisViewModel(private val jenisPropertiRepository: JenisPropertiRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertJenisUiState())
        private set

    // Update the UI state based on user input
    fun updateInsertJenisState(insertJenisUiEvent: InsertJenisUiEvent) {
        uiState = InsertJenisUiState(insertJenisUiEvent = insertJenisUiEvent)
    }

    // Insert a new JenisProperti
    fun insertJenisProperti() {
        viewModelScope.launch {
            try {
                // Validate input before attempting to insert
                if (validateInput(uiState.insertJenisUiEvent)) {
                    jenisPropertiRepository.insertJenisProperti(uiState.insertJenisUiEvent.toJenisProperti())
                } else {
                    throw IllegalArgumentException("Input tidak valid")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Validate the user input
    private fun validateInput(event: InsertJenisUiEvent): Boolean {
        return event.namaJenis.isNotBlank() && event.deskripsiJenis.isNotBlank()
    }
}

// Data class to represent the form inputs for JenisProperti
data class InsertJenisUiEvent(
    val idJenis: Int = 0,
    val namaJenis: String = "",
    val deskripsiJenis: String = ""
)

// Convert InsertJenisUiEvent to JenisProperti model
fun InsertJenisUiEvent.toJenisProperti(): JenisProperti = JenisProperti(
    idJenis = idJenis,
    namaJenis = namaJenis,
    deskripsiJenis = deskripsiJenis
)

// UI State to hold the form input event
data class InsertJenisUiState(
    val insertJenisUiEvent: InsertJenisUiEvent = InsertJenisUiEvent()
)

// Extension function to convert a JenisProperti model to the UI event
fun JenisProperti.toInsertJenisUiEvent(): InsertJenisUiEvent = InsertJenisUiEvent(
    idJenis = idJenis,
    namaJenis = namaJenis,
    deskripsiJenis = deskripsiJenis
)

// Extension function to convert JenisProperti to the UI state
fun JenisProperti.toUiStateJenis(): InsertJenisUiState = InsertJenisUiState(
    insertJenisUiEvent = toInsertJenisUiEvent()
)