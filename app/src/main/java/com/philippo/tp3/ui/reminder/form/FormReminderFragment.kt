package com.philippo.tp3.ui.reminder.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.philippo.tp3.R
import com.philippo.tp3.dao.ReminderDaoFirestore
import com.philippo.tp3.model.Reminder
import com.philippo.tp3.model.ReminderUtil
import kotlinx.android.synthetic.main.form_reminder_fragment.*

class FormReminderFragment : Fragment() {
    private lateinit var viewModel: FormReminderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireActivity().application
        val formReminderViewModelFactory = FormReminderViewModelFactory(
            ReminderDaoFirestore(), application)

        viewModel = ViewModelProvider(this, formReminderViewModelFactory)
            .get(FormReminderViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner){ status ->
            if (status)
                findNavController().popBackStack()
        }

        return inflater.inflate(R.layout.form_reminder_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ReminderUtil.reminderSelected != null)
            fillForm(ReminderUtil.reminderSelected!!)


        btnSaveFormReminder.setOnClickListener {
            val name = editTextFormReminderName.text.toString()
            val type = editTextFormReminderType.text.toString()
            val text = editTextFormReminderText.text.toString()

            viewModel.insertReminder(name, type, text)

            Navigation.findNavController(it).navigate(R.id.action_formReminderFragment_to_listReminderFragment)
        }
    }

    private fun fillForm(reminder: Reminder) {
        editTextFormReminderName.setText(reminder.name)
        editTextFormReminderType.setText(reminder.type)
        editTextFormReminderText.setText(reminder.text)
        btnSaveFormReminder.text = "Update"
    }
}