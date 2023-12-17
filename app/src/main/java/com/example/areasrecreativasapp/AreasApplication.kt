package com.example.areasrecreativasapp

import android.app.Application
import com.example.areasrecreativasapp.data.database.AreaDatabase

class AreasApplication : Application() {
    val database: AreaDatabase by lazy { AreaDatabase.getDatabase(this) }
}