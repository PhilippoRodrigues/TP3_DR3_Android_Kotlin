package com.philippo.tp3.model

import com.google.firebase.firestore.DocumentId

class User (
    var nome: String? = null,
    var dataNascimento: String? = null,
    var username: String? = null,
    @DocumentId
    val uid: String? = null
)