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

    fun saveRegister(email: String, password: String, nome: String, dataNascimento: String, username: String, ) {
        UserDao
            .saveRegister(email, password)
            .addOnSuccessListener {
                if (it.user != null)
                    saveProfileInfos(
                        it.user!!.uid,
                        nome,
                        username,
                        dataNascimento
                    )
            }
            .addOnFailureListener {
                changeMessage("${it.message}")
            }
            .addOnFailureListener {
                changeMessage("${it.message}")
            }
    }

    private fun saveProfileInfos(
        userId: String,
        nome: String,
        username: String,
        dataNascimento: String
    ) {
        UserDao
            .saveorUpdateUserProfile(
                userId, nome, username, dataNascimento)

            .addOnSuccessListener {
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


