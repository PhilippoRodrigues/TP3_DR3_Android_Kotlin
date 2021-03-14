package com.philippo.tp3.ui.user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun verifyUser(email: String, password: String) {
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Logged!"
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }
    }
}