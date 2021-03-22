package com.philippo.api.model

open class ReminderApi (
    var id: Long? = null,
    var description: String? = null,
    var name: String? = null,
    var owner: User? = null
) {
    override fun toString(): String = "$name\n\t${owner?.full_name_display}"
}