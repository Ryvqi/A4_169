package com.example.bisnisproperti.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.JenisProperti
import com.example.bisnisproperti.repository.JenisPropertiRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeJenisUiState {
    data class Success(
        val jenisProperti: List<JenisProperti>
    ) : HomeJenisUiState()

    object Error : HomeJenisUiState()
    object Loading : HomeJenisUiState()
}

class HomeJenisViewModel(private val jenisPropertiRepository: JenisPropertiRepository) : ViewModel() {
    var jenisPropertiUiState: HomeJenisUiState by mutableStateOf(HomeJenisUiState.Loading)
        private set

    init {
        getAllJenisProperti()
    }

    fun getAllJenisProperti() {
        viewModelScope.launch {
            jenisPropertiUiState = HomeJenisUiState.Loading
            jenisPropertiUiState = try {
                HomeJenisUiState.Success(jenisPropertiRepository.getAllJenisProperti().data)
            } catch (e: IOException) {
                HomeJenisUiState.Error
            } catch (e: HttpException) {
                HomeJenisUiState.Error
            }
        }
    }

    fun deleteJenisProperti(idJenis: Int) {
        viewModelScope.launch {
            try {
                jenisPropertiRepository.deleteJenisProperti(idJenis.toString())
                getAllJenisProperti() // Refresh list after deletion
            } catch (e: IOException) {
                jenisPropertiUiState = HomeJenisUiState.Error
            } catch (e: HttpException) {
                jenisPropertiUiState = HomeJenisUiState.Error
            }
        }
    }
}