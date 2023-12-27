package com.example.areasrecreativasapp

import android.app.Application
import com.example.areasrecreativasapp.data.database.AppDatabase

class AreasApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}