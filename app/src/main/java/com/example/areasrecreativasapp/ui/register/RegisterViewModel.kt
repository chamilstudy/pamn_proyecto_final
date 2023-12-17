package com.example.areasrecreativasapp.ui.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _mode = MutableLiveData<Boolean>()

    private val auth : FirebaseAuth = Firebase.auth

    fun createUserWithEmailAndPassword(email : String, password : String, home : () -> Unit)
            = viewModelScope.launch {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("RegEmailPass", "Register Completed")
                        home()
                    } else {
                        Log.d("RegEmailPass", "Register Failed")
                    }
                }
        } catch (ex : Exception) {
            Log.d("RegisterEmailPass", "signInWithEmailAndPassword: ${ex.message}")
        }
    }

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    suspend fun onLoginSelected(function : () -> Unit) {
        _isLoading.value = true
        function()
        delay(2000)
        _isLoading.value = false
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isValidPassword(password: String): Boolean = password.length > 6
}