package com.example.bisnisproperti.model

import kotlinx.serialization.*

@Serializable
data class Properti(
    @SerialName("id_properti")
    val idProperti: Int,

    @SerialName("nama_properti")
    val namaProperti: String,

    @SerialName("deskripsi_properti")
    val deskripsiProperti: String,

    val lokasi: String,
    val harga: String,

    @SerialName("status_properti")
    val statusProperti: String,

    @SerialName("id_jenis")
    val idJenis: Int,

    @SerialName("id_pemilik")
    val idPemilik: Int,

    @SerialName("id_manajer")
    val idManajer: Int
)

@Serializable
data class AllPropertiResponse(
    val status: Boolean,
    val message: String,
    val data: List<Properti>
)

@Serializable
data class PropertiDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Properti
)