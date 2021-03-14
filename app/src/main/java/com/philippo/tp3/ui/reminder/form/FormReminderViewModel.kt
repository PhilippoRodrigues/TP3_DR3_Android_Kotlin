package com.philippo.tp3.ui.reminder.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.philippo.tp3.dao.ReminderDao
import com.philippo.tp3.model.Reminder

class FormReminderViewModel(private val reminderDao: ReminderDao, application: Application) :
    AndroidViewModel(application) {

    private val app = application

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String?>()
    val msg: MutableLiveData<String?> = _msg

    init {
        _status.value = false
        _msg.value = null
    }

    fun insertReminder(
        name: String, type: String, text: String
    ) {

        val reminder = Reminder(name, type, text, checked = false)
        reminderDao.insert(reminder)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Reminder added successfully!"
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }
    }

}