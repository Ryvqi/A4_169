package com.example.bisnisproperti.repository

import com.example.bisnisproperti.model.AllPropertiResponse
import com.example.bisnisproperti.model.Properti
import com.example.bisnisproperti.service.PropertiService

interface PropertiRepository {
    suspend fun getAllProperti(): AllPropertiResponse
    suspend fun getPropertiById(id: String): Properti
    suspend fun insertProperti(properti: Properti)
    suspend fun updateProperti(id: String, properti: Properti)
    suspend fun deleteProperti(id: String): String
}

class NetworkPropertiRepository(
    private val propertiService: PropertiService
) : PropertiRepository {
    override suspend fun getAllProperti() = propertiService.getAllProperti()

    override suspend fun getPropertiById(id: String) = propertiService.getPropertiById(id)

    override suspend fun insertProperti(properti: Properti) = propertiService.insertProperti(properti)

    override suspend fun updateProperti(id: String, properti: Properti) =
        propertiService.updateProperti(id, properti)

    override suspend fun deleteProperti(id: String): String {
        val response = propertiService.deleteProperti(id)
        return if (response.isSuccessful) {
            "Properti berhasil dihapus"
        } else {
            "Gagal menghapus properti: ${response.errorBody()?.string() ?: "Unknown error"}"
        }
    }
}