package com.philippo.tp3.model

open class Reminder(
    var name: String? = null,
    var id: Long? = null
) {
    override fun toString(): String = "$name}"
}