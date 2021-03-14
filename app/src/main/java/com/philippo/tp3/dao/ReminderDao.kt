package com.philippo.tp3.dao

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.philippo.tp3.model.Reminder

interface ReminderDao {

    fun insert(reminder: Reminder): Task<Void>

    fun delete(reminder: Reminder) : Task<Void>

    fun get(name: String): Task<DocumentSnapshot>

    fun selectByType(type: String): Task<QuerySnapshot>

    fun all(): CollectionReference
}