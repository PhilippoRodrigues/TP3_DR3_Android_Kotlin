package com.philippo.tp3.ui.reminder.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.philippo.tp3.api.ApiClient
import com.philippo.tp3.model.Reminder
import kotlinx.coroutines.launch

class ListReminderViewModel(private val userId: Long?) : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _reminders = MutableLiveData<List<Reminder>>()
    val reminders: LiveData<List<Reminder>> = _reminders

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        viewModelScope.launch {
            try {
                val reminderService = ApiClient.getReminderService()

                _reminders.value =
                    if (userId == null) reminderService.all() else reminderService.allByReminder(userId)
            } catch (e: Exception) {
                _message.value = "${e.message}"
            }
        }
    }

    fun encerrarSessao() {
        firebaseAuth.signOut()
    }
}