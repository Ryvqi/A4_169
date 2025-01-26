package com.example.bisnisproperti

import android.app.Application
import com.example.bisnisproperti.dependesiinjeksi.AppContainer
import com.example.bisnisproperti.dependesiinjeksi.PropertiContainer

class RealEstateApp: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = PropertiContainer()
    }
}