package com.example.areasrecreativasapp.ui.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areasrecreativasapp.data.database.Book
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {

    //private val auth : FirebaseAuth = Firebase.auth
    private val userId = Firebase.auth.currentUser?.uid
    private val db = FirebaseFirestore.getInstance()

    private val _bookListFlow = MutableStateFlow<List<Book>>(emptyList())
    val bookListFlow : StateFlow<List<Book>> = _bookListFlow

    private val _isLoading = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> = _isLoading

    init {
        getBooks()
    }

    private fun getBooks() {
        _isLoading.value = true
        db.collection("users")
            .document(userId.toString())
            .collection("books")
            .get()
            .addOnSuccessListener { result ->
                val books = result.map { document ->
                    Book (
                        id = document.id,
                        name = document.getString("name") ?: "",
                        dayIn = document.getString("in") ?: "",
                        dayOut = document.getString("out") ?: "",
                        persons = document.getString("persons") ?: "",
                        tents = document.getString("tents") ?: "",
                        campers = document.getString("campers") ?: ""
                    )
                }
                _bookListFlow.value = books
                Log.w("GetData", "Success getting ${_bookListFlow.value}")
            }
            .addOnFailureListener { exception ->
                Log.w("GetData", "Error getting documents.", exception)
            }
        _isLoading.value = false
    }

    fun removeBook(bookId: String) {

        _isLoading.value = true
        FirebaseFirestore
            .getInstance()
            .collection("users")
            .document(userId.toString())
            .collection("books")
            .document(bookId)
            .delete()
            .addOnSuccessListener {
                getBooks()
                Log.d("RemoveData", "Removed $bookId")
            }.addOnFailureListener{
                Log.d("RemoveData", "Error $it")
            }
        _isLoading.value = false
    }
}