package com.example.bisnisproperti.dependesiinjeksi

import com.example.bisnisproperti.repository.JenisPropertiRepository
import com.example.bisnisproperti.repository.ManajerPropertiRepository
import com.example.bisnisproperti.repository.NetworkJenisPropertiRepository
import com.example.bisnisproperti.repository.NetworkManajerPropertiRepository
import com.example.bisnisproperti.repository.NetworkPemilikRepository
import com.example.bisnisproperti.repository.NetworkPropertiRepository
import com.example.bisnisproperti.repository.PemilikRepository
import com.example.bisnisproperti.repository.PropertiRepository
import com.example.bisnisproperti.service.JenisPropertiService
import com.example.bisnisproperti.service.ManajerPropertiService
import com.example.bisnisproperti.service.PemilikService
import com.example.bisnisproperti.service.PropertiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val propertiRepository: PropertiRepository
    val jenisPropertiRepository: JenisPropertiRepository
    val pemilikRepository: PemilikRepository
    val manajerPropertiRepository: ManajerPropertiRepository
}

class PropertiContainer : AppContainer {

    private val baseUrl = "http://10.0.2.2:3000/api/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()


}