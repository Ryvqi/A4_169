package com.example.bisnisproperti.model

import kotlinx.serialization.*

@Serializable
data class ManajerProperti(
    @SerialName("id_manajer")
    val idManajer: Int,

    @SerialName("nama_manajer")
    val namaManajer: String,

    @SerialName("kontak_manajer")
    val kontakManajer: String
)

@Serializable
data class AllManajerPropertiResponse(
    val status: Boolean,
    val message: String,
    val data: List<ManajerProperti>
)

@Serializable
data class ManajerPropertiDetailResponse(
    val status: Boolean,
    val message: String,
    val data: ManajerProperti
)