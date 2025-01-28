package com.example.bisnisproperti.ui.viewmodel.pemilik

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bisnisproperti.RealEstateApp

object PenyediaVMPemilik {
    val Factory = viewModelFactory {
        initializer { HomePemilikViewModel(aplikasiProperti().container.pemilikRepository) }
        initializer { InsertPemilikViewModel(aplikasiProperti().container.pemilikRepository) }
        initializer { DetailPemilikViewModel(createSavedStateHandle(),aplikasiProperti().container.pemilikRepository) }
        initializer { UpdatePemilikViewModel(createSavedStateHandle(),aplikasiProperti().container.pemilikRepository) }
    }
}

fun CreationExtras.aplikasiProperti(): RealEstateApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RealEstateApp)