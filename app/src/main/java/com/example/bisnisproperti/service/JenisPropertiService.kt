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

    @GET("jenis_properti/{id_jenis}")
    suspend fun getJenisPropertiById(@Path("id_jenis") idJenis: String): JenisProperti

    @POST("jenis_properti/store")
    suspend fun insertJenisProperti(@Body jenisProperti: JenisProperti): Response<JenisProperti>

    @PUT("jenis_properti/{id_jenis}")
    suspend fun updateJenisProperti(
        @Path("id_jenis") idJenis: String,
        @Body jenisProperti: JenisProperti
    ): Response<JenisProperti>

    @DELETE("jenis_properti/{id_jenis}")
    suspend fun deleteJenisProperti(@Path("id_jenis") idJenis: String): Response<Void>
}