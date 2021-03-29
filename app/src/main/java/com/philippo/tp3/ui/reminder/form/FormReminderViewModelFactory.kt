package com.philippo.tp3.ui.reminder.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.philippo.tp3.dao.ReminderDao

//class FormReminderViewModelFactory(private val reminderDao: ReminderDao, private val application: Application): ViewModelProvider.Factory {

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(FormReminderViewModel::class.java))
//            return FormReminderViewModel(reminderDao, application) as T
//        throw IllegalArgumentException("Unknown viewModel class.")
//    }
//}