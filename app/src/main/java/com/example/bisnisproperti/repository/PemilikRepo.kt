package com.example.bisnisproperti.repository

import com.example.bisnisproperti.model.AllPemilikResponse
import com.example.bisnisproperti.model.Pemilik
import com.example.bisnisproperti.service.PemilikService

interface PemilikRepository {
    suspend fun getAllPemilik(): AllPemilikResponse
    suspend fun getPemilikById(id: String): Pemilik
    suspend fun insertPemilik(pemilik: Pemilik)
    suspend fun updatePemilik(id: String, pemilik: Pemilik)
    suspend fun deletePemilik(id: String): String
}

class NetworkPemilikRepository(
    private val pemilikService: PemilikService
) : PemilikRepository {
    override suspend fun getAllPemilik() = pemilikService.getAllPemilik()

    override suspend fun getPemilikById(id: String) = pemilikService.getPemilikById(id)

    override suspend fun insertPemilik(pemilik: Pemilik) = pemilikService.insertPemilik(pemilik)

    override suspend fun updatePemilik(id: String, pemilik: Pemilik) =
        pemilikService.updatePemilik(id, pemilik)

    override suspend fun deletePemilik(id: String): String {
        val response = pemilikService.deletePemilik(id)
        return if (response.isSuccessful) {
            "Pemilik berhasil dihapus"
        } else {
            "Gagal menghapus pemilik: ${response.errorBody()?.string() ?: "Unknown error"}"
        }
    }
}