package com.philippo.api.model

class User (
    val id: Long? = null,
    var name : String? = null,
    var email : String? = null
) {
    override fun toString(): String = "$name"
}