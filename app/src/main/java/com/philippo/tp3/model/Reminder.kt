package com.philippo.tp3.model

open class Reminder(
    var id: Long? = null,
    var name: String? = null,
//    var content: String ? = null,
//    var owner: UserApi? = null
) {
    override fun toString(): String = "$name}"
}