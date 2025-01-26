package com.example.bisnisproperti.service

import com.example.bisnisproperti.model.AllPemilikResponse
import com.example.bisnisproperti.model.Pemilik
import retrofit2.Response
import retrofit2.http.*

interface PemilikService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("pemilik")
    suspend fun getAllPemilik(): AllPemilikResponse

    @GET("pemilik/{id}")
    suspend fun getPemilikById(@Path("id") id: String): Pemilik

    @POST("pemilik")
    suspend fun insertPemilik(@Body pemilik: Pemilik)

    @PUT("pemilik/{id}")
    suspend fun updatePemilik(@Path("id") id: String, @Body pemilik: Pemilik)

    @DELETE("pemilik/{id}")
    suspend fun deletePemilik(@Path("id") id: String): Response<Void>
}