package com.philippo.api.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.philippo.api.model.ReminderApi

class DashboardViewModelFactory(private val reminderId: Long): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(reminderId) as T
    }
}