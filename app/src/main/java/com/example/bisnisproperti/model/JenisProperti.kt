package com.example.bisnisproperti.model

import kotlinx.serialization.*

@Serializable
data class JenisProperti(
    @SerialName("id_jenis")
    val idJenis: Int,

    @SerialName("nama_jenis")
    val namaJenis: String,

    @SerialName("deskripsi_jenis")
    val deskripsiJenis: String
)

@Serializable
data class AllJenisPropertiResponse(
    val status: Boolean,
    val message: String,
    val data: List<JenisProperti>
)

@Serializable
data class JenisPropertiDetailResponse(
    val status: Boolean,
    val message: String,
    val data: JenisProperti
)