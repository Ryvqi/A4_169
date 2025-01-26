package com.example.bisnisproperti.service

import com.example.bisnisproperti.model.AllJenisPropertiResponse
import com.example.bisnisproperti.model.JenisProperti
import retrofit2.Response
import retrofit2.http.*

interface JenisPropertiService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("jenis_properti")
    suspend fun getAllJenisProperti(): AllJenisPropertiResponse

    @GET("jenis_properti/{id}")
    suspend fun getJenisPropertiById(@Path("id") id: String): JenisProperti

    @POST("jenis_properti")
    suspend fun insertJenisProperti(@Body jenisProperti: JenisProperti)

    @PUT("jenis_properti/{id}")
    suspend fun updateJenisProperti(@Path("id") id: String, @Body jenisProperti: JenisProperti)

    @DELETE("jenis_properti/{id}")
    suspend fun deleteJenisProperti(@Path("id") id: String): Response<Void>
}