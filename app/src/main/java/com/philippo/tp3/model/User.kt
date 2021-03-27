package com.philippo.tp3.model

import com.google.firebase.firestore.DocumentId

class User (
    var username: String? = null,
    var nome: String? = null,
    var dataNascimento: String? = null,
    @DocumentId
    val uid: String? = null
)