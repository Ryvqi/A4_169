package com.example.bisnisproperti.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.JenisProperti
import com.example.bisnisproperti.repository.JenisPropertiRepository
import com.example.bisnisproperti.ui.view.jenis.DestinasiDetailJenis
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailJenisUiState {
    data class Success(
        val jenisProperti: JenisProperti
    ) : DetailJenisUiState()

    object Error : DetailJenisUiState()
    object Loading : DetailJenisUiState()
}

class DetailJenisViewModel(
    savedStateHandle: SavedStateHandle,
    private val jenisPropertiRepository: JenisPropertiRepository
) : ViewModel() {
    var jenisPropertiUiState: DetailJenisUiState by mutableStateOf(DetailJenisUiState.Loading)
        private set

    private val idJenis: Int = checkNotNull(savedStateHandle[DestinasiDetailJenis.idJenis])

    init {
        getJenisPropertiById()
    }

    fun getJenisPropertiById() {
        viewModelScope.launch {
            jenisPropertiUiState = DetailJenisUiState.Loading
            jenisPropertiUiState = try {
                DetailJenisUiState.Success(jenisPropertiRepository.getJenisPropertiById(idJenis.toString()))
            } catch (e: IOException) {
                DetailJenisUiState.Error
            } catch (e: HttpException) {
                DetailJenisUiState.Error
            }
        }
    }
}

fun JenisProperti.toDetailJenisUiEvent(): InsertJenisUiEvent {
    return InsertJenisUiEvent(
        idJenis = idJenis,
        namaJenis = namaJenis,
        deskripsiJenis = deskripsiJenis
    )
}