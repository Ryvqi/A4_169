package com.example.bisnisproperti.repository

import com.example.bisnisproperti.model.AllJenisPropertiResponse
import com.example.bisnisproperti.model.JenisProperti
import com.example.bisnisproperti.service.JenisPropertiService

interface JenisPropertiRepository {
    suspend fun getAllJenisProperti(): AllJenisPropertiResponse
    suspend fun getJenisPropertiById(id: String): JenisProperti
    suspend fun insertJenisProperti(jenisProperti: JenisProperti)
    suspend fun updateJenisProperti(id: String, jenisProperti: JenisProperti)
    suspend fun deleteJenisProperti(id: String): String
}

class NetworkJenisPropertiRepository(
    private val jenisPropertiService: JenisPropertiService
) : JenisPropertiRepository {
    override suspend fun getAllJenisProperti() = jenisPropertiService.getAllJenisProperti()

    override suspend fun getJenisPropertiById(id: String) = jenisPropertiService.getJenisPropertiById(id)

    override suspend fun insertJenisProperti(jenisProperti: JenisProperti) =
        jenisPropertiService.insertJenisProperti(jenisProperti)

    override suspend fun updateJenisProperti(id: String, jenisProperti: JenisProperti) =
        jenisPropertiService.updateJenisProperti(id, jenisProperti)

    override suspend fun deleteJenisProperti(id: String): String {
        val response = jenisPropertiService.deleteJenisProperti(id)
        return if (response.isSuccessful) {
            "Jenis Properti berhasil dihapus"
        } else {
            "Gagal menghapus jenis properti: ${response.errorBody()?.string() ?: "Unknown error"}"
        }
    }
}