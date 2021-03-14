package com.philippo.tp3.ui.reminder.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.philippo.tp3.dao.ReminderDao
import com.philippo.tp3.model.Reminder
import com.philippo.tp3.model.ReminderUtil

class ListReminderViewModel(private val reminderDao: ReminderDao) : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _reminders = MutableLiveData<List<Reminder>>()
    val reminders: LiveData<List<Reminder>> = _reminders

    private val _reminder = MutableLiveData<Reminder>()
    val reminder: LiveData<Reminder> = _reminder

    fun updateRemindersList() {
        reminderDao.all().addSnapshotListener { snapshot, error ->
            if (error != null) Log.i("Reminders", "${error.message}")
            else
                if (snapshot != null && !snapshot.isEmpty)
                    _reminders.value = snapshot.toObjects(Reminder::class.java)
        }
    }

    fun deleteReminder(reminder: Reminder) {
        reminderDao.delete(reminder)
    }

    fun selectReminder(reminder: Reminder) {
        _reminder.value = reminder

        reminderDao.get(reminder.name!!)
    }

        fun encerrarSessao() {
        firebaseAuth.signOut()
    }
}