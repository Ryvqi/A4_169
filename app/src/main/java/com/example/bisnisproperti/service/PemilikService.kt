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

    @GET("pemilik/{id_pemilik}")
    suspend fun getPemilikById(@Path("id_pemilik") idPemilik: String): Pemilik

    @POST("pemilik/store")
    suspend fun insertPemilik(@Body pemilik: Pemilik): Response<Pemilik>

    @PUT("pemilik/{id_pemilik}")
    suspend fun updatePemilik(
        @Path("id_pemilik") idPemilik: String,
        @Body pemilik: Pemilik
    ): Response<Pemilik>

    @DELETE("pemilik/{id_pemilik}")
    suspend fun deletePemilik(@Path("id_pemilik") idPemilik: String): Response<Void>
}