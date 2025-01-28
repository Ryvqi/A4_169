package com.example.bisnisproperti.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.Pemilik
import com.example.bisnisproperti.repository.PemilikRepository
import kotlinx.coroutines.launch

class InsertPemilikViewModel(private val pemilikRepository: PemilikRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertPemilikUiState())
        private set

    fun updateInsertPemilikState(insertPemilikUiEvent: InsertPemilikUiEvent) {
        uiState = InsertPemilikUiState(insertPemilikUiEvent = insertPemilikUiEvent)
    }

    fun insertPemilik() {
        viewModelScope.launch {
            try {
                // Validasi input
                if (validateInput(uiState.insertPemilikUiEvent)) {
                    val pemilik = uiState.insertPemilikUiEvent.toPemilik()
                    val response = pemilikRepository.insertPemilik(pemilik)
                    if (response.isSuccessful) {
                        // Berhasil menyimpan data
                        uiState = uiState.copy(successMessage = "Pemilik berhasil disimpan.")
                    } else {
                        throw Exception("Gagal menyimpan data: ${response.errorBody()?.string() ?: "Unknown error"}")
                    }
                } else {
                    throw IllegalArgumentException("Input tidak valid")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(errorMessage = e.message ?: "Terjadi kesalahan")
            }
        }
    }

    private fun validateInput(event: InsertPemilikUiEvent): Boolean {
        return event.namaPemilik.isNotBlank() &&
                event.kontakPemilik.isNotBlank()
    }
}

data class InsertPemilikUiEvent(
    val idPemilik: Int = 0,
    val namaPemilik: String = "",
    val kontakPemilik: String = ""
)

fun InsertPemilikUiEvent.toPemilik(): Pemilik = Pemilik(
    idPemilik = idPemilik,
    namaPemilik = namaPemilik,
    kontakPemilik = kontakPemilik
)

data class InsertPemilikUiState(
    val insertPemilikUiEvent: InsertPemilikUiEvent = InsertPemilikUiEvent(),
    val successMessage: String? = null,
    val errorMessage: String? = null
)

fun Pemilik.toInsertPemilikUiEvent(): InsertPemilikUiEvent = InsertPemilikUiEvent(
    idPemilik = idPemilik,
    namaPemilik = namaPemilik,
    kontakPemilik = kontakPemilik
)