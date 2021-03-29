package com.philippo.tp3.model

class UserApi (
    val id: Long? = null,
    var name : String? = null,
) {
    override fun toString(): String = "$name"
}