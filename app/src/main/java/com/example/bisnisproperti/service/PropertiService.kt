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

    @GET("properti/{id_properti}")
    suspend fun getPropertiById(@Path("id_properti") idProperti: String): Properti

    @POST("properti/store")
    suspend fun insertProperti(@Body properti: Properti): Response<Properti>

    @PUT("properti/{id_properti}")
    suspend fun updateProperti(
        @Path("id_properti") idProperti: String,
        @Body properti: Properti
    ): Response<Properti>

    @DELETE("properti/{id_properti}")
    suspend fun deleteProperti(@Path("id_properti") idProperti: String): Response<Void>
}