package com.philippo.tp3.dao

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

object UserDao {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun saveRegister(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }
}