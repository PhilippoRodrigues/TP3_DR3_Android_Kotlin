package com.philippo.tp3.ui.reminder.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListReminderViewModelFactory(private val reminderId: Long?): ViewModelProvider.Factory {

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ListReminderViewModel::class.java))
//            return ListReminderViewModel(reminderDao) as T
//        throw IllegalArgumentException("Unknown viewModel class 😢")
//    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =  ListReminderViewModel(reminderId) as T
}