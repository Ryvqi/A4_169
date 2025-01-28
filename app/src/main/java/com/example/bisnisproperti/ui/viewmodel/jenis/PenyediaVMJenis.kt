package com.example.bisnisproperti.ui.viewmodel.jenis

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bisnisproperti.RealEstateApp

object PenyediaVMJenis {
    val Factory = viewModelFactory {
        initializer { HomeJenisViewModel(aplikasiProperti().container.jenisPropertiRepository) }
        initializer { InsertJenisViewModel(aplikasiProperti().container.jenisPropertiRepository) }
        initializer { DetailJenisViewModel(createSavedStateHandle(),aplikasiProperti().container.jenisPropertiRepository) }
        initializer { UpdateJenisViewModel(createSavedStateHandle(),aplikasiProperti().container.jenisPropertiRepository) }
    }
}

fun CreationExtras.aplikasiProperti(): RealEstateApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RealEstateApp)