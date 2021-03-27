package com.philippo.tp3.dao

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.philippo.tp3.model.User

object UserDao {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val collection = FirebaseFirestore.getInstance().collection("users")

    private val storageReference = FirebaseStorage
        .getInstance()
        .reference
        .child("users")

    fun saveRegister(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

    fun saveorUpdateUserProfile(
        uid: String,
        nome: String,
        username: String,
        dataNascimento: String
    ): Task<Void> {
        return collection
            .document(uid)
            .set(User(nome, username, dataNascimento))
    }

    fun selectUserByUid(uid: String): Task<DocumentSnapshot> {
        return collection.document(uid).get()
    }
}