package com.example.bisnisproperti.ui.viewmodel.properti

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bisnisproperti.RealEstateApp

object PenyediaVMProperti {
    val Factory = viewModelFactory {
        initializer { HomePropertiViewModel(aplikasiProperti().container.propertiRepository) }
        initializer { InsertPropertiViewModel(aplikasiProperti().container.propertiRepository) }
        initializer { DetailPropertiViewModel(createSavedStateHandle(),aplikasiProperti().container.propertiRepository) }
        initializer { UpdatePropertiViewModel(createSavedStateHandle(),aplikasiProperti().container.propertiRepository) }
    }
}

fun CreationExtras.aplikasiProperti(): RealEstateApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RealEstateApp)