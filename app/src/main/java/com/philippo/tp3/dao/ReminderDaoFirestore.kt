package com.philippo.tp3.dao

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.philippo.tp3.model.Reminder

class ReminderDaoFirestore : ReminderDao {

    private val collection = FirebaseFirestore
        .getInstance()
        .collection("reminders")

    override fun insert(reminder: Reminder): Task<Void> {
        return collection
            .document(reminder.name!!)
            .set(reminder)
    }
    override fun delete(reminder: Reminder): Task<Void> {
        return collection
            .document(reminder.name!!)
            .delete()
    }
    override fun get(name: String): Task<DocumentSnapshot> {
        return collection
            .document(name)
            .get()
    }

    override fun selectByType(type: String): Task<QuerySnapshot> {
        return collection
            .whereEqualTo("type", type)
            .get()
    }

    override fun all(): CollectionReference {
        return collection
    }
}