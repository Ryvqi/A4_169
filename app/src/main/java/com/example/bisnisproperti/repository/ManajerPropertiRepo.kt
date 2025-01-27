package com.example.bisnisproperti.repository

import com.example.bisnisproperti.model.AllManajerPropertiResponse
import com.example.bisnisproperti.model.ManajerProperti
import com.example.bisnisproperti.service.ManajerPropertiService
import retrofit2.Response

interface ManajerPropertiRepository {
    suspend fun getAllManajerProperti(): AllManajerPropertiResponse
    suspend fun getManajerPropertiById(id: String): ManajerProperti
    suspend fun insertManajerProperti(manajerProperti: ManajerProperti): Response<ManajerProperti>
    suspend fun updateManajerProperti(id: String, manajerProperti: ManajerProperti): Response<ManajerProperti>
    suspend fun deleteManajerProperti(id: String): String
}

class NetworkManajerPropertiRepository(
    private val manajerPropertiService: ManajerPropertiService
) : ManajerPropertiRepository {
    override suspend fun getAllManajerProperti() = manajerPropertiService.getAllManajerProperti()

    override suspend fun getManajerPropertiById(id: String) = manajerPropertiService.getManajerPropertiById(id)

    override suspend fun insertManajerProperti(manajerProperti: ManajerProperti) =
        manajerPropertiService.insertManajerProperti(manajerProperti)

    override suspend fun updateManajerProperti(id: String, manajerProperti: ManajerProperti) =
        manajerPropertiService.updateManajerProperti(id, manajerProperti)

    override suspend fun deleteManajerProperti(id: String): String {
        val response = manajerPropertiService.deleteManajerProperti(id)
        return if (response.isSuccessful) {
            "Manajer Properti berhasil dihapus"
        } else {
            "Gagal menghapus manajer properti: ${response.errorBody()?.string() ?: "Unknown error"}"
        }
    }
}