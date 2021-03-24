package com.philippo.api.model

open class ReminderApi (
    var id: Long? = null,
    var name: String? = null,
    var content: String? = null,
    var owner: User? = null
) {
    override fun toString(): String = "$name\n\t$content\n\t${owner?.name}"
}