package com.philippo.api.model

class ReminderApi (
    var id: Long? = null,
    var description: String? = null,
    var name: String? = null,
    var owner: Owner? = null
) {
    override fun toString(): String = "$name\n\t$description"
}