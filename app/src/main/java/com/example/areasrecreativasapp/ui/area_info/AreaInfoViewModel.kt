package com.example.areasrecreativasapp.ui.area_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.areasrecreativasapp.AreasApplication
import com.example.areasrecreativasapp.data.database.Area
import com.example.areasrecreativasapp.data.database.AreaDao
import kotlinx.coroutines.flow.Flow

class AreaInfoViewModel(private val areaDao: AreaDao) : ViewModel() {
    fun getArea(id: Int): Flow<Area> = areaDao.getArea(id)

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AreasApplication)
                AreaInfoViewModel(application.database.areaDao())
            }
        }
    }
}