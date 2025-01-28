package com.example.bisnisproperti.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.JenisProperti
import com.example.bisnisproperti.repository.JenisPropertiRepository
import kotlinx.coroutines.launch

class UpdateJenisViewModel(
    savedStateHandle: SavedStateHandle,
    private val jenisPropertiRepository: JenisPropertiRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertJenisUiState())
        private set

    private val idJenis: Int = checkNotNull(savedStateHandle["id_jenis"])

    init {
        viewModelScope.launch {
            try {
                val jenisProperti = jenisPropertiRepository.getJenisPropertiById(idJenis.toString())
                uiState = jenisProperti.toInsertJenisUiState()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateJenis() {
        viewModelScope.launch {
            try {
                // Konversi UI state menjadi objek JenisProperti
                val jenisProperti = uiState.insertJenisUiEvent.toJenisProperti()
                jenisPropertiRepository.updateJenisProperti(
                    idJenis.toString(),
                    jenisProperti
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateState(insertJenisUiEvent: InsertJenisUiEvent) {
        uiState = uiState.copy(insertJenisUiEvent = insertJenisUiEvent)
    }
}

fun JenisProperti.toInsertJenisUiState(): InsertJenisUiState = InsertJenisUiState(
    insertJenisUiEvent = this.toInsertJenisUiEvent()
)