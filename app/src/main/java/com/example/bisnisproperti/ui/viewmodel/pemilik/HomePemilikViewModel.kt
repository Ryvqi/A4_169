package com.example.bisnisproperti.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.Pemilik
import com.example.bisnisproperti.repository.PemilikRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomePemilikUiState {
    data class Success(
        val pemilik: List<Pemilik>
    ) : HomePemilikUiState()

    object Error : HomePemilikUiState()
    object Loading : HomePemilikUiState()
}

class HomePemilikViewModel(private val pemilikRepository: PemilikRepository) : ViewModel() {
    var pemilikUiState: HomePemilikUiState by mutableStateOf(HomePemilikUiState.Loading)
        private set

    init {
        getAllPemilik()
    }

    fun getAllPemilik() {
        viewModelScope.launch {
            pemilikUiState = HomePemilikUiState.Loading
            pemilikUiState = try {
                HomePemilikUiState.Success(pemilikRepository.getAllPemilik().data)
            } catch (e: IOException) {
                HomePemilikUiState.Error
            } catch (e: HttpException) {
                HomePemilikUiState.Error
            }
        }
    }

    fun deletePemilik(idPemilik: Int) {
        viewModelScope.launch {
            try {
                pemilikRepository.deletePemilik(idPemilik.toString())
                getAllPemilik() // Refresh list after deletion
            } catch (e: IOException) {
                pemilikUiState = HomePemilikUiState.Error
            } catch (e: HttpException) {
                pemilikUiState = HomePemilikUiState.Error
            }
        }
    }
}