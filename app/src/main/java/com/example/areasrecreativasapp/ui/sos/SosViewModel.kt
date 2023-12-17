package com.example.areasrecreativasapp.ui.sos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.areasrecreativasapp.AreasApplication
import com.example.areasrecreativasapp.data.database.Sos
import com.example.areasrecreativasapp.data.database.SosDao
import kotlinx.coroutines.flow.Flow

class SosViewModel(private val sosDao: SosDao) : ViewModel() {
    fun getSos(id: Int): Flow<Sos> = sosDao.getSos(id)
    fun getAllSos(): Flow<List<Sos>> = sosDao.getAllSos()

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AreasApplication)
                SosViewModel(application.database.sosDao())
            }
        }
    }
}