package com.example.bisnisproperti.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.Properti
import com.example.bisnisproperti.repository.PropertiRepository
import kotlinx.coroutines.launch

class UpdatePropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val propertiRepository: PropertiRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertPropertiUiState())
        private set

    private val idProperti: Int = checkNotNull(savedStateHandle["id_properti"])

    init {
        viewModelScope.launch {
            val properti = propertiRepository.getPropertiById(idProperti.toString())
            uiState = properti.toInsertpropertiUiEvent()
        }
    }

    fun updateProperti() {
        viewModelScope.launch {
            try {
                val properti = uiState.insertPropertiUiEvent.toProperti()
                propertiRepository.updateProperti(idProperti.toString(), properti)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateState(insertPropertiUiEvent: InsertPropertiUiEvent) {
        uiState = uiState.copy(insertPropertiUiEvent = insertPropertiUiEvent)
    }
}

fun Properti.toInsertpropertiUiEvent(): InsertPropertiUiState = InsertPropertiUiState(
    insertPropertiUiEvent = this.toDetailPropertiUiEvent()
)