package com.philippo.tp3.ui.user.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.philippo.tp3.dao.UserDao
import com.philippo.tp3.model.User

class UserProfileViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser = MutableLiveData<FirebaseUser>()
    val user = MutableLiveData<User>()

    init {
        firebaseUser.value = firebaseAuth.currentUser

        UserDao.selectUserByUid(firebaseUser.value!!.uid)
            .addOnSuccessListener {
                val selectedUser = it.toObject(User::class.java)
                user.value = selectedUser!!
            }
    }
}