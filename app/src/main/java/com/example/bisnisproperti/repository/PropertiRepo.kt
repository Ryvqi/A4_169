package com.example.bisnisproperti.repository

import com.example.bisnisproperti.model.AllPropertiResponse
import com.example.bisnisproperti.model.Properti

interface PropertiRepository {
    suspend fun getAllProperti(): AllPropertiResponse
    suspend fun getPropertiById(id: String): Properti
    suspend fun insertProperti(properti: Properti)
    suspend fun updateProperti(id: String, properti: Properti)
    suspend fun deleteProperti(id: String): String
}

