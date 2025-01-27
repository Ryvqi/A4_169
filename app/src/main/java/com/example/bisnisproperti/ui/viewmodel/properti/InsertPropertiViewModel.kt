package com.example.bisnisproperti.ui.viewmodel.properti

import com.example.bisnisproperti.model.Properti

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

fun InsertPropertiUiEvent.toProperti(): Properti = Properti(
    idProperti = idProperti,
    namaProperti = namaProperti,
    deskripsiProperti = deskripsiProperti,
    lokasi = lokasi,
    harga = harga,
    statusProperti = statusProperti,
    idJenis = idJenis,
    idPemilik = idPemilik,
    idManajer = idManajer
)

data class InsertPropertiUiState(
    val insertPropertiUiEvent: InsertPropertiUiEvent = InsertPropertiUiEvent()
)

fun Properti.toInsertPropertiUiEvent(): InsertPropertiUiEvent = InsertPropertiUiEvent(
    idProperti = idProperti,
    namaProperti = namaProperti,
    deskripsiProperti = deskripsiProperti,
    lokasi = lokasi,
    harga = harga,
    statusProperti = statusProperti,
    idJenis = idJenis,
    idPemilik = idPemilik,
    idManajer = idManajer
)

fun Properti.toUiStateProperti(): InsertPropertiUiState = InsertPropertiUiState(
    insertPropertiUiEvent = toInsertPropertiUiEvent()
)