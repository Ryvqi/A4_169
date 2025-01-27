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
import retrofit2.HttpException
import java.io.IOException

sealed class DetailPropertiUiState {
    data class Success(
        val properti: Properti
    ) : DetailPropertiUiState()

    object Error : DetailPropertiUiState()
    object Loading : DetailPropertiUiState()
}

class DetailPropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val propertiRepository: PropertiRepository
) : ViewModel() {
    var propertiUiState: DetailPropertiUiState by mutableStateOf(DetailPropertiUiState.Loading)
        private set

    private val idProperti: Int = checkNotNull(savedStateHandle["id_properti"])

    init {
        getPropertiById()
    }

    fun getPropertiById() {
        viewModelScope.launch {
            propertiUiState = DetailPropertiUiState.Loading
            propertiUiState = try {
                DetailPropertiUiState.Success(propertiRepository.getPropertiById(idProperti.toString()))
            } catch (e: IOException) {
                DetailPropertiUiState.Error
            } catch (e: HttpException) {
                DetailPropertiUiState.Error
            }
        }
    }
}

fun Properti.toDetailPropertiUiEvent(): InsertPropertiUiEvent{
    return InsertPropertiUiEvent(
        idProperti = idProperti,
        namaProperti = namaProperti,
        deskripsiProperti = deskripsiProperti,
        lokasi = lokasi,
        harga = harga,
        statusProperti = statusProperti,
        idJenis = idJenis,
        idPemilik = idPemilik,
        idManajer = idManajer
    )
}