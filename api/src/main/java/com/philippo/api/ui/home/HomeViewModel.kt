package com.philippo.api.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philippo.api.model.ReminderApi
import com.philippo.api.retrofit.ApiClient
import kotlinx.coroutines.launch

class HomeViewModel(private val userId: Long?) : ViewModel() {

    private val _reminders = MutableLiveData<List<ReminderApi>>()
    val reminders: LiveData<List<ReminderApi>> = _reminders

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        viewModelScope.launch{
            try {
                val reminderService = ApiClient.getReminderService()

                _reminders.value = if (userId == null) reminderService.all("Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6") else reminderService.allByReminder(userId)
            } catch (e: Exception) {
                _message.value = "${e.message}"
            }
        }
    }
}