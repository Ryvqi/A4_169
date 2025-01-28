package com.example.bisnisproperti.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.Pemilik
import com.example.bisnisproperti.repository.PemilikRepository
import com.example.bisnisproperti.ui.view.pemilik.DestinasiDetailPemilik
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailPemilikUiState {
    data class Success(
        val pemilik: Pemilik
    ) : DetailPemilikUiState()

    object Error : DetailPemilikUiState()
    object Loading : DetailPemilikUiState()
}

class DetailPemilikViewModel(
    savedStateHandle: SavedStateHandle,
    private val pemilikRepository: PemilikRepository
) : ViewModel() {
    var pemilikUiState: DetailPemilikUiState by mutableStateOf(DetailPemilikUiState.Loading)
        private set

    private val idPemilik: Int = checkNotNull(savedStateHandle[DestinasiDetailPemilik.idPemilik])

    init {
        getPemilikById()
    }

    fun getPemilikById() {
        viewModelScope.launch {
            pemilikUiState = DetailPemilikUiState.Loading
            pemilikUiState = try {
                val pemilik = pemilikRepository.getPemilikById(idPemilik.toString())
                DetailPemilikUiState.Success(pemilik)
            } catch (e: IOException) {
                DetailPemilikUiState.Error
            } catch (e: HttpException) {
                DetailPemilikUiState.Error
            }
        }
    }
}

fun Pemilik.toDetailPemilikUiEvent(): InsertPemilikUiEvent {
    return InsertPemilikUiEvent(
        idPemilik = idPemilik,
        namaPemilik = namaPemilik,
        kontakPemilik = kontakPemilik
    )
}