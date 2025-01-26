package com.example.bisnisproperti.repository

import com.example.bisnisproperti.model.AllManajerPropertiResponse
import com.example.bisnisproperti.model.ManajerProperti

interface ManajerPropertiRepository {
    suspend fun getAllManajerProperti(): AllManajerPropertiResponse
    suspend fun getManajerPropertiById(id: String): ManajerProperti
    suspend fun insertManajerProperti(manajerProperti: ManajerProperti)
    suspend fun updateManajerProperti(id: String, manajerProperti: ManajerProperti)
    suspend fun deleteManajerProperti(id: String): String
}

