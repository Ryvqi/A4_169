package com.example.bisnisproperti.service

import com.example.bisnisproperti.model.AllPropertiResponse
import com.example.bisnisproperti.model.Properti
import retrofit2.Response
import retrofit2.http.*

interface PropertiService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("properti")
    suspend fun getAllProperti(): AllPropertiResponse

    @GET("properti/{id}")
    suspend fun getPropertiById(@Path("id") id: String): Properti

    @POST("properti")
    suspend fun insertProperti(@Body properti: Properti)

    @PUT("properti/{id}")
    suspend fun updateProperti(@Path("id") id: String, @Body properti: Properti)

    @DELETE("properti/{id}")
    suspend fun deleteProperti(@Path("id") id: String): Response<Void>
}