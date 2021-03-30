package com.philippo.tp3.ui.reminder.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.philippo.tp3.R
import com.philippo.tp3.model.Reminder
import com.philippo.tp3.model.ReminderUtil
import kotlinx.android.synthetic.main.form_reminder_fragment.*

class FormReminderFragment : Fragment() {
    private lateinit var viewModel: FormReminderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.form_reminder_fragment, container, false)

        viewModel = ViewModelProvider(this).get(FormReminderViewModel::class.java)

        viewModel.let { vm ->
            vm.status.observe(viewLifecycleOwner) {
                if (it)
                    findNavController().popBackStack()
            }
            vm.msg.observe(viewLifecycleOwner) {
                if (!it.isNullOrBlank())
                    Snackbar
                        .make(
                            view,
                            it,
                            Snackbar.LENGTH_LONG
                        ).show()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ReminderUtil.reminderSelected != null)
            fillForm(ReminderUtil.reminderSelected!!)


        btnSaveFormReminder.setOnClickListener {
            val name = editTextFormReminderName.text.toString()

            viewModel.saveReminder(name)

            Navigation.findNavController(it).navigate(R.id.action_formReminderFragment_to_listReminderFragment)
        }
    }

    private fun fillForm(reminder: Reminder) {
        editTextFormReminderName.setText(reminder.name)
        btnSaveFormReminder.text = "Update"
    }
}