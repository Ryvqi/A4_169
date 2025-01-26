package com.example.bisnisproperti.repository

import com.example.bisnisproperti.model.AllJenisPropertiResponse
import com.example.bisnisproperti.model.JenisProperti

interface JenisPropertiRepository {
    suspend fun getAllJenisProperti(): AllJenisPropertiResponse
    suspend fun getJenisPropertiById(id: String): JenisProperti
    suspend fun insertJenisProperti(jenisProperti: JenisProperti)
    suspend fun updateJenisProperti(id: String, jenisProperti: JenisProperti)
    suspend fun deleteJenisProperti(id: String): String
}

