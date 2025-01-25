package com.example.bisnisproperti.model

import kotlinx.serialization.*

@Serializable
data class Pemilik(
    @SerialName("id_pemilik")
    val idPemilik: Int,

    @SerialName("nama_pemilik")
    val namaPemilik: String,

    @SerialName("kontak_pemilik")
    val kontakPemilik: String
)

@Serializable
data class AllPemilikResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pemilik>
)

@Serializable
data class PemilikDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pemilik
)