package com.philippo.tp3.ui.reminder.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListReminderViewModelFactory(private val reminderId: Long?): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =  ListReminderViewModel(reminderId) as T
}