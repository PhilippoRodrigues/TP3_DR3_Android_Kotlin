package com.philippo.api.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philippo.api.model.ReminderApi
import com.philippo.api.retrofit.ApiClient
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _reminders = MutableLiveData<List<ReminderApi>>()
    val reminders: LiveData<List<ReminderApi>> = _reminders

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        viewModelScope.launch{
            try {
                val reminderService = ApiClient.getReminderService()
                val reminders  = reminderService.all()
                _reminders.value = reminders
            } catch (e: Exception) {
                _message.value = "${e.message}"
            }
        }
    }
}