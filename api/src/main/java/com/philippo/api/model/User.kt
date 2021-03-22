package com.philippo.api.model

class User (
    val id: Long? = null,
    var full_name_display : String? = null
) {
    override fun toString(): String = "$full_name_display"
}