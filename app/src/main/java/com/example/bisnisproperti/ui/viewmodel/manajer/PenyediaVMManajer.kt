package com.example.bisnisproperti.ui.viewmodel.manajer

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bisnisproperti.RealEstateApp

object PenyediaVMManajer {
    val Factory = viewModelFactory {
        initializer { HomeManajerViewModel(aplikasiProperti().container.manajerPropertiRepository) }
        initializer { InsertManajerViewModel(aplikasiProperti().container.manajerPropertiRepository) }
        initializer { DetailManajerViewModel(createSavedStateHandle(),aplikasiProperti().container.manajerPropertiRepository) }
        initializer { UpdateManajerViewModel(createSavedStateHandle(),aplikasiProperti().container.manajerPropertiRepository) }
    }
}

fun CreationExtras.aplikasiProperti(): RealEstateApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RealEstateApp)