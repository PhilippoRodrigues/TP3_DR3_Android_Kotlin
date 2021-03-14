package com.philippo.tp3.ui.reminder.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.philippo.tp3.dao.ReminderDao

class ListReminderViewModelFactory(private val reminderDao: ReminderDao): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListReminderViewModel::class.java))
            return ListReminderViewModel(reminderDao) as T
        throw IllegalArgumentException("Unknown viewModel class 😢")
    }
}