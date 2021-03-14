package com.philippo.tp3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.philippo.tp3.dao.ReminderDao
import com.philippo.tp3.dao.ReminderDaoFirestore
import com.philippo.tp3.model.Reminder
import com.philippo.tp3.model.ReminderUtil

class ShowReminderViewModel : ViewModel() {

    private val _reminder = MutableLiveData<Reminder?>()
    val reminder: LiveData<Reminder?> = _reminder


    init {

        val reminder = ReminderUtil.reminderSelected
        if (reminder != null){
            _reminder.value = reminder
        }
    }
}