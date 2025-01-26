package com.example.bisnisproperti.repository

import com.example.bisnisproperti.model.AllPemilikResponse
import com.example.bisnisproperti.model.Pemilik

interface PemilikRepository {
    suspend fun getAllPemilik(): AllPemilikResponse
    suspend fun getPemilikById(id: String): Pemilik
    suspend fun insertPemilik(pemilik: Pemilik)
    suspend fun updatePemilik(id: String, pemilik: Pemilik)
    suspend fun deletePemilik(id: String): String
}

