package com.example.bisnisproperti.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisnisproperti.model.ManajerProperti
import com.example.bisnisproperti.repository.ManajerPropertiRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailManajerUiState {
    data class Success(
        val manajerProperti: ManajerProperti
    ) : DetailManajerUiState()

    object Error : DetailManajerUiState()
    object Loading : DetailManajerUiState()
}

class DetailManajerViewModel(
    savedStateHandle: SavedStateHandle,
    private val manajerPropertiRepository: ManajerPropertiRepository
) : ViewModel() {
    var manajerUiState: DetailManajerUiState by mutableStateOf(DetailManajerUiState.Loading)
        private set

    private val idManajer: Int = checkNotNull(savedStateHandle["id_manajer"])

    init {
        getManajerById()
    }

    fun getManajerById() {
        viewModelScope.launch {
            manajerUiState = DetailManajerUiState.Loading
            manajerUiState = try {
                val manajerProperti = manajerPropertiRepository.getManajerPropertiById(idManajer.toString())
                DetailManajerUiState.Success(manajerProperti)
            } catch (e: IOException) {
                DetailManajerUiState.Error
            } catch (e: HttpException) {
                DetailManajerUiState.Error
            }
        }
    }
}

fun ManajerProperti.toDetailManajerUiEvent(): InsertManajerUiEvent {
    return InsertManajerUiEvent(
        idManajer = idManajer,
        namaManajer = namaManajer,
        kontakManajer = kontakManajer
    )
}