package com.philippo.tp3.model

class ReminderDetail (
    id: Long? = null,
    name: String? = null,
//    content: String ? = null,
//    owner: UserApi? = null,
    var members: List<Reminder>
): Reminder(id, name)