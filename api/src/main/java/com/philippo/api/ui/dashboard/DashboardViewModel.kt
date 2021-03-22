package com.philippo.api.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philippo.api.dao.ReminderApiService
import com.philippo.api.model.ReminderDetail
import com.philippo.api.retrofit.ApiClient
import kotlinx.coroutines.launch

class DashboardViewModel(private val reminderId: Long) : ViewModel() {

    private val _reminder = MutableLiveData<ReminderDetail>()
    val reminder: LiveData<ReminderDetail> = _reminder

    init {
        viewModelScope.launch {
            _reminder.value = ApiClient.getReminderService().show(reminderId)
        }
    }
}