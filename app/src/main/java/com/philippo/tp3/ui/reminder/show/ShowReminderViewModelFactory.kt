package com.philippo.tp3.ui.reminder.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShowReminderViewModelFactory(private val reminderId: Long): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowReminderViewModel(reminderId) as T
    }
}