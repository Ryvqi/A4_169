package com.example.bisnisproperti.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.ManajerProperti
import com.example.bisnisproperti.repository.ManajerPropertiRepository
import kotlinx.coroutines.launch

class InsertManajerViewModel(private val manajerRepository: ManajerPropertiRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertManajerUiState())
        private set

    fun updateInsertManajerState(insertManajerUiEvent: InsertManajerUiEvent) {
        uiState = InsertManajerUiState(insertManajerUiEvent = insertManajerUiEvent)
    }

    fun insertManajer() {
        viewModelScope.launch {
            try {
                // Validasi input
                if (validateInput(uiState.insertManajerUiEvent)) {
                    val manajerProperti = uiState.insertManajerUiEvent.toManajerProperti()
                    val response = manajerRepository.insertManajerProperti(manajerProperti)
                    if (response.isSuccessful) {
                        // Berhasil menyimpan data
                        uiState = uiState.copy(successMessage = "Manajer berhasil disimpan.")
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

    private fun validateInput(event: InsertManajerUiEvent): Boolean {
        return event.namaManajer.isNotBlank() &&
                event.kontakManajer.isNotBlank()
    }
}

data class InsertManajerUiEvent(
    val idManajer: Int = 0,
    val namaManajer: String = "",
    val kontakManajer: String = ""
)

fun InsertManajerUiEvent.toManajerProperti(): ManajerProperti = ManajerProperti(
    idManajer = idManajer,
    namaManajer = namaManajer,
    kontakManajer = kontakManajer
)

data class InsertManajerUiState(
    val insertManajerUiEvent: InsertManajerUiEvent = InsertManajerUiEvent(),
    val successMessage: String? = null,
    val errorMessage: String? = null
)

fun ManajerProperti.toInsertManajerUiEvent(): InsertManajerUiEvent = InsertManajerUiEvent(
    idManajer = idManajer,
    namaManajer = namaManajer,
    kontakManajer = kontakManajer
)