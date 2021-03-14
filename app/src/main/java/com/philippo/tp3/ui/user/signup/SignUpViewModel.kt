package com.philippo.tp3.ui.user.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.philippo.tp3.dao.UserDao

class SignUpViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun saveRegister(email: String, password: String) {
        UserDao
            .saveRegister(email, password)
            .addOnSuccessListener {
                _status.value = true
                changeMessage("User successfully registered!")
            }
            .addOnFailureListener {
                changeMessage("${it.message}")
            }
    }

    fun changeMessage(msg: String) {
        _msg.value = msg
    }
}


