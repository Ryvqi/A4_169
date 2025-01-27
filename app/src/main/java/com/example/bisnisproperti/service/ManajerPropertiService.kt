package com.example.bisnisproperti.service

import com.example.bisnisproperti.model.AllManajerPropertiResponse
import com.example.bisnisproperti.model.ManajerProperti
import retrofit2.Response
import retrofit2.http.*

interface ManajerPropertiService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("manajer_properti")
    suspend fun getAllManajerProperti(): AllManajerPropertiResponse

    @GET("manajer_properti/{id_manajer}")
    suspend fun getManajerPropertiById(@Path("id_manajer") idManajer: String): ManajerProperti

    @POST("manajer_properti/store")
    suspend fun insertManajerProperti(@Body manajerProperti: ManajerProperti): Response<ManajerProperti>

    @PUT("manajer_properti/{id_manajer}")
    suspend fun updateManajerProperti(
        @Path("id_manajer") idManajer: String,
        @Body manajerProperti: ManajerProperti
    ): Response<ManajerProperti>

    @DELETE("manajer_properti/{id_manajer}")
    suspend fun deleteManajerProperti(@Path("id_manajer") idManajer: String): Response<Void>
}