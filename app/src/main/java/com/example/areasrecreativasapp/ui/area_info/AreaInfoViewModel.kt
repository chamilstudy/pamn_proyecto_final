package com.example.areasrecreativasapp.ui.area_info

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.areasrecreativasapp.AreasApplication
import com.example.areasrecreativasapp.data.database.Area
import com.example.areasrecreativasapp.data.database.AreaDao
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AreaInfoViewModel(private val areaDao: AreaDao, areaId : String) : ViewModel() {
    private val _numOfPersons = MutableLiveData<String>()
    val numOfPersons : LiveData<String> = _numOfPersons

    private val _numOfCampers = MutableLiveData<String>()
    val numOfCampers : LiveData<String> = _numOfCampers

    private val _numOfTents = MutableLiveData<String>()
    val numOfTents : LiveData<String> = _numOfTents

    private val _dateToEnter = MutableLiveData<String>()
    val dateToEnter : LiveData<String> = _dateToEnter

    private val _dateToExit = MutableLiveData<String>()
    val dateToExit : LiveData<String> = _dateToExit

    private val _requestBookEnable = MutableLiveData<Boolean>()
    val requestBookEnable : LiveData<Boolean> = _requestBookEnable

    val area : Flow<Area> = areaDao.getArea(areaId.toInt())


    companion object {
        fun factory(areaId: String) : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AreasApplication)
                AreaInfoViewModel(application.database.areaDao(), areaId)
            }
        }
    }


    private val auth : FirebaseAuth = Firebase.auth
    private val userId = auth.currentUser?.uid
    fun sendBook(name : String, persons : String, tents : String, campers : String, dayIn : String, dayOut: String) {
        val book = mutableMapOf<String, Any>()

        book["name"] = name
        book["in"] = dayIn
        book["out"] = dayOut
        book["persons"] = persons
        book["tents"] = tents
        book["campers"] = campers

        FirebaseFirestore
            .getInstance()
            .collection("users")
            .document(userId.toString())
            .collection("books")
            .add(book)
            .addOnSuccessListener {
                Log.d("SaveData", "Saved ${it.id}")
            }.addOnFailureListener{
                Log.d("SaveData", "Error $it")
            }
    }

    fun onBookReset () {
        _numOfPersons.value = ""
        _numOfCampers.value = ""
        _numOfTents.value = ""
        _dateToEnter.value = ""
        _dateToExit.value = ""
    }
     fun onBookChange(
         numOfPersons: String,
         numOfCampers: String,
         numOfTents: String,
         dateToEnter: String,
         dateToExit: String,
         area : Area
    ) {
         _numOfPersons.value = if (area.capacitypeople == 0) "0" else numOfPersons
         _numOfCampers.value = if (area.capacitycampers == 0) "0" else numOfCampers
         _numOfTents.value = if (area.capacitytents == 0) "0" else  numOfTents
         _dateToEnter.value = dateToEnter
         _dateToExit.value = dateToExit
         _requestBookEnable.value = isValidNumOfPersons(numOfPersons, area)
            && isValidNumOfCampers(numOfCampers, area)
            && isValidNumOfTents(numOfTents, area)
            && isValidDate(dateToEnter, dateToExit)
    }

    private fun isValidNumOfPersons(numOfPersons : String, area : Area) : Boolean {

        return  numOfPersons.toIntOrNull() != null
                && numOfPersons.toInt() >= 0
                && numOfPersons.toInt() <= area.capacitypeople
    }

    private fun isValidNumOfCampers(numOfCampers : String, area : Area) : Boolean {
        return numOfCampers.toIntOrNull() != null
                && numOfCampers.toInt() >= 0
                && numOfCampers.toInt() <= area.capacitycampers
    }

    private fun isValidNumOfTents(numOfTents : String, area : Area) : Boolean {
        return numOfTents.toIntOrNull() != null
                && numOfTents.toInt() >= 0
                && numOfTents.toInt() <= area.capacitytents
    }

    private fun isValidDate(dateIn : String, dateOut : String) : Boolean {
        return dateIn.toIntOrNull() != null
                && dateOut.toIntOrNull() != null
                && dateIn.toInt() > 0
                && dateOut.toInt() > 0
                && dateIn.toInt() < dateOut.toInt()
                && dateIn.toInt() < 32
                && dateOut.toInt() < 32
    }
}