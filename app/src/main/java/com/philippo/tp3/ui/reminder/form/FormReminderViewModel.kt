package com.philippo.tp3.ui.reminder.form

import androidx.lifecycle.*
import com.philippo.tp3.api.ApiClient
import com.philippo.tp3.model.Reminder
import kotlinx.coroutines.launch
import java.lang.Exception

class FormReminderViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: MutableLiveData<String> = _msg

    fun saveReminder(name: String) {
        viewModelScope.launch {
            try {
                val reminder = Reminder(name)
                val reminderService = ApiClient.getReminderService()
                reminderService.createReminder(reminder)
                _status.value = true
                _msg.value = "Reminder created!"
            } catch (e: Exception){
                _msg.value = "${e.message}"
            }
        }
    }
}