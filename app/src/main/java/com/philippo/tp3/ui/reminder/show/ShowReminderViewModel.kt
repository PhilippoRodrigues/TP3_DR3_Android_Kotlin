package com.philippo.tp3.ui.reminder.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philippo.tp3.api.ApiClient
import com.philippo.tp3.model.Reminder
import com.philippo.tp3.model.ReminderDetail
import kotlinx.coroutines.launch

class ShowReminderViewModel(private val reminderId: Long) : ViewModel() {

    private val reminderService = ApiClient.getReminderService()

    private val _reminder = MutableLiveData<ReminderDetail>()
    val reminder: LiveData<ReminderDetail> = _reminder

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        _status.value = true

        if (reminderId == null)
            _status.value = false
        else
            viewModelScope.launch {
                _reminder.value = ApiClient.getReminderService().show(reminderId)
            }
    }

    fun delete() {
        viewModelScope.launch {
            try {
                reminderService.deleteReminder(reminderId!!)
                _status.value = false
            } catch (e: java.lang.Exception) {
                _message.value = "${e.message}"
            }
        }
    }
}