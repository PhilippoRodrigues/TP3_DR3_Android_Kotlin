package com.philippo.api.model

class ReminderDetail (
    id: Long? = null,
    description: String? = null,
    name: String? = null,
    owner: User? = null,
    var members: List<User>

): ReminderApi(id, description, name, owner)