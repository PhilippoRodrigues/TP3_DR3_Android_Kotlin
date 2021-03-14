package com.philippo.tp3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.philippo.tp3.adapter.RecyclerListReminder
import com.philippo.tp3.model.Reminder
import com.philippo.tp3.model.ReminderUtil
import kotlinx.android.synthetic.main.list_reminder_fragment.*
import kotlinx.android.synthetic.main.show_reminder_fragment.*

class ShowReminderFragment : Fragment() {

    private lateinit var viewModel: ShowReminderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(ShowReminderViewModel::class.java)

        viewModel.reminder.observe(viewLifecycleOwner) {
            if (it != null) {
                showReminderFields(it)
            }
        }

        return inflater.inflate(R.layout.show_reminder_fragment, container, false)
    }

    private fun showReminderFields(reminder: Reminder) {
        textViewShowReminderName.text = reminder.name
        textViewShowReminderType.text = reminder.type
        textViewShowReminderText.text = reminder.text
    }
}