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

//    init {
//        _status.value = false
//        _msg.value = null
//    }

    fun saveReminder(name: String) {
        viewModelScope.launch {
            try {
                val reminder = Reminder(id=0, name)
                val reminderService = ApiClient.getReminderService()
                reminderService.createReminder(reminder)
                _status.value = true
                _msg.value = "Reminder created!"
            } catch (e: Exception){
                _msg.value = "${e.message}"
            }
        }
    }

//    fun insertReminder(
//        name: String, type: String, text: String
//    ) {

//        val reminder = Reminder(name, type, text)
//        reminderDao.insert(reminder)
//            .addOnSuccessListener {
//                _status.value = true
//                _msg.value = "Reminder added successfully!"
//            }
//            .addOnFailureListener {
//                _msg.value = "${it.message}"
//            }
//    }

}