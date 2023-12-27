package com.example.areasrecreativasapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.test.core.app.ApplicationProvider
import com.example.areasrecreativasapp.AreasApplication
import com.example.areasrecreativasapp.data.database.Area
import com.example.areasrecreativasapp.data.database.AreaDao
import com.example.areasrecreativasapp.data.database.AppDatabase
import com.example.areasrecreativasapp.states.AreasState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val areaDao: AreaDao) : ViewModel() {
    // Get full bus schedule from Room DB
    fun getAllAreas(): Flow<List<Area>> = areaDao.getAllAreas()

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AreasApplication)
                HomeViewModel(application.database.areaDao())
            }
        }
    }
}