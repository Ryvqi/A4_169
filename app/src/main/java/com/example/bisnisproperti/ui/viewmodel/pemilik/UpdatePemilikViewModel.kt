package com.example.bisnisproperti.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.Pemilik
import com.example.bisnisproperti.repository.PemilikRepository
import com.example.bisnisproperti.ui.view.pemilik.DestinasiUpdatePemilik
import kotlinx.coroutines.launch

class UpdatePemilikViewModel(
    savedStateHandle: SavedStateHandle,
    private val pemilikRepository: PemilikRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertPemilikUiState())
        private set

    private val idPemilik: Int = checkNotNull(savedStateHandle[DestinasiUpdatePemilik.idPemilik])

    init {
        viewModelScope.launch {
            try {
                val pemilik = pemilikRepository.getPemilikById(idPemilik.toString())
                uiState = pemilik.toInsertPemilikUiState()
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(errorMessage = "Gagal memuat data: ${e.message}")
            }
        }
    }

    fun updatePemilik() {
        viewModelScope.launch {
            try {
                // Konversi UI state menjadi objek Pemilik
                val pemilik = uiState.insertPemilikUiEvent.toPemilik()
                val response = pemilikRepository.updatePemilik(
                    idPemilik.toString(),
                    pemilik
                )
                if (response.isSuccessful) {
                    uiState = uiState.copy(successMessage = "Pemilik berhasil diperbarui.")
                } else {
                    throw Exception("Gagal memperbarui data: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(errorMessage = e.message ?: "Terjadi kesalahan")
            }
        }
    }

    fun updateState(insertPemilikUiEvent: InsertPemilikUiEvent) {
        uiState = uiState.copy(insertPemilikUiEvent = insertPemilikUiEvent)
    }
}

fun Pemilik.toInsertPemilikUiState(): InsertPemilikUiState = InsertPemilikUiState(
    insertPemilikUiEvent = this.toInsertPemilikUiEvent()
)