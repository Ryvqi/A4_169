package com.example.bisnisproperti.dependesiinjeksi

import com.example.bisnisproperti.repository.JenisPropertiRepository
import com.example.bisnisproperti.repository.ManajerPropertiRepository
import com.example.bisnisproperti.repository.PemilikRepository
import com.example.bisnisproperti.repository.PropertiRepository

interface AppContainer {
    val propertiRepository: PropertiRepository
    val jenisPropertiRepository: JenisPropertiRepository
    val pemilikRepository: PemilikRepository
    val manajerPropertiRepository: ManajerPropertiRepository
}

