package com.example.bisnisproperti.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.Properti
import com.example.bisnisproperti.repository.PropertiRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomePropertiUiState {
    data class Success(
        val properti: List<Properti>
    ) : HomePropertiUiState()

    object Error : HomePropertiUiState()
    object Loading : HomePropertiUiState()
}

class HomePropertiViewModel(private val propertiRepository: PropertiRepository) : ViewModel() {
    var propertiUiState: HomePropertiUiState by mutableStateOf(HomePropertiUiState.Loading)
        private set

    init {
        getAllProperti()
    }

    fun getAllProperti() {
        viewModelScope.launch {
            propertiUiState = HomePropertiUiState.Loading
            propertiUiState = try {
                HomePropertiUiState.Success(propertiRepository.getAllProperti().data)
            } catch (e: IOException) {
                HomePropertiUiState.Error
            } catch (e: HttpException) {
                HomePropertiUiState.Error
            }
        }
    }

    fun deleteProperti(idProperti: Int) {
        viewModelScope.launch {
            try {
                propertiRepository.deleteProperti(idProperti.toString())
                getAllProperti() // Refresh list after deletion
            } catch (e: IOException) {
                propertiUiState = HomePropertiUiState.Error
            } catch (e: HttpException) {
                propertiUiState = HomePropertiUiState.Error
            }
        }
    }
}