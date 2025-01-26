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

    @GET("manajer_properti/{id}")
    suspend fun getManajerPropertiById(@Path("id") id: String): ManajerProperti

    @POST("manajer_properti")
    suspend fun insertManajerProperti(@Body manajerProperti: ManajerProperti)

    @PUT("manajer_properti/{id}")
    suspend fun updateManajerProperti(@Path("id") id: String, @Body manajerProperti: ManajerProperti)

    @DELETE("manajer_properti/{id}")
    suspend fun deleteManajerProperti(@Path("id") id: String): Response<Void>
}