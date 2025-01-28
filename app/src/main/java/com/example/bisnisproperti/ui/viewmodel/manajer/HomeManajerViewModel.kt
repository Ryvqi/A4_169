package com.example.bisnisproperti.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.ManajerProperti
import com.example.bisnisproperti.repository.ManajerPropertiRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeManajerUiState {
    data class Success(
        val manajer: List<ManajerProperti>
    ) : HomeManajerUiState()

    object Error : HomeManajerUiState()
    object Loading : HomeManajerUiState()
}

class HomeManajerViewModel(private val manajerRepository: ManajerPropertiRepository) : ViewModel() {
    var manajerUiState: HomeManajerUiState by mutableStateOf(HomeManajerUiState.Loading)
        private set

    init {
        getAllManajer()
    }

    fun getAllManajer() {
        viewModelScope.launch {
            manajerUiState = HomeManajerUiState.Loading
            manajerUiState = try {
                HomeManajerUiState.Success(manajerRepository.getAllManajerProperti().data)
            } catch (e: IOException) {
                HomeManajerUiState.Error
            } catch (e: HttpException) {
                HomeManajerUiState.Error
            }
        }
    }

    fun deleteManajer(idManajer: Int) {
        viewModelScope.launch {
            try {
                manajerRepository.deleteManajerProperti(idManajer.toString())
                getAllManajer() // Refresh list after deletion
            } catch (e: IOException) {
                manajerUiState = HomeManajerUiState.Error
            } catch (e: HttpException) {
                manajerUiState = HomeManajerUiState.Error
            }
        }
    }
}