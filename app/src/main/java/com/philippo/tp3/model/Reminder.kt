package com.philippo.tp3.model

import com.google.firebase.firestore.DocumentId

class Reminder (
    @DocumentId
    var name: String? = null,

    val type: String? = null,
    val text: String ? = null,
    val checked: Boolean? = false,
)