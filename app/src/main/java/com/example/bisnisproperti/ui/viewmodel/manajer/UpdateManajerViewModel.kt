package com.example.bisnisproperti.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.ManajerProperti
import com.example.bisnisproperti.repository.ManajerPropertiRepository
import com.example.bisnisproperti.ui.view.manajer.DestinasiUpdateManajer
import kotlinx.coroutines.launch

class UpdateManajerViewModel(
    savedStateHandle: SavedStateHandle,
    private val manajerPropertiRepository: ManajerPropertiRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertManajerUiState())
        private set

    private val idManajer: Int = checkNotNull(savedStateHandle[DestinasiUpdateManajer.idManajer])

    init {
        viewModelScope.launch {
            try {
                val manajerProperti = manajerPropertiRepository.getManajerPropertiById(idManajer.toString())
                uiState = manajerProperti.toInsertManajerUiState()
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(errorMessage = "Gagal memuat data: ${e.message}")
            }
        }
    }

    fun updateManajer() {
        viewModelScope.launch {
            try {
                // Konversi UI state menjadi objek ManajerProperti
                val manajerProperti = uiState.insertManajerUiEvent.toManajerProperti()
                val response = manajerPropertiRepository.updateManajerProperti(
                    idManajer.toString(),
                    manajerProperti
                )
                if (response.isSuccessful) {
                    uiState = uiState.copy(successMessage = "Manajer berhasil diperbarui.")
                } else {
                    throw Exception("Gagal memperbarui data: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(errorMessage = e.message ?: "Terjadi kesalahan")
            }
        }
    }

    fun updateState(insertManajerUiEvent: InsertManajerUiEvent) {
        uiState = uiState.copy(insertManajerUiEvent = insertManajerUiEvent)
    }
}

fun ManajerProperti.toInsertManajerUiState(): InsertManajerUiState = InsertManajerUiState(
    insertManajerUiEvent = this.toInsertManajerUiEvent()
)