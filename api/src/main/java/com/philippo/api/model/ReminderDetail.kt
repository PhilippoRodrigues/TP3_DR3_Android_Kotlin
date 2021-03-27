package com.philippo.api.model

class ReminderDetail (
    id: Long? = null,
    name: String? = null,
    content: String? = null,
    owner: User? = null,
    var members: List<ReminderApi>

): ReminderApi(id, name, content, owner)