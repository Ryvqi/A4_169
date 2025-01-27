package com.example.bisnisproperti.ui.viewmodel.properti

data class InsertPropertiUiEvent(
    val idProperti: Int = 0,
    val namaProperti: String = "",
    val deskripsiProperti: String = "",
    val lokasi: String = "",
    val harga: String = "",
    val statusProperti: String = "",
    val idJenis: Int = 0,
    val idPemilik: Int = 0,
    val idManajer: Int = 0
)