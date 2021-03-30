package com.philippo.tp3.ui.reminder.show

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.philippo.tp3.R
import com.philippo.tp3.model.Reminder
import com.philippo.tp3.model.ReminderDetail
import kotlinx.android.synthetic.main.show_reminder_fragment.*

class ShowReminderFragment : Fragment() {

    private lateinit var reminderViewModel: ShowReminderViewModel
    private lateinit var showReminderViewModelFactory: ShowReminderViewModelFactory

    private lateinit var textViewShowReminderName: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val reminderId = arguments?.getLong("reminderId")

        if (reminderId == null)
            findNavController().popBackStack()


        showReminderViewModelFactory = ShowReminderViewModelFactory(reminderId!!)

        reminderViewModel = ViewModelProvider(
            this,
            showReminderViewModelFactory
        ).get(ShowReminderViewModel::class.java)

        val root = inflater.inflate(R.layout.show_reminder_fragment, container, false)

        textViewShowReminderName = root.findViewById(R.id.textViewShowReminderShowName)

        reminderViewModel.reminder.observe(viewLifecycleOwner) {
            if (it != null) {
                showReminderFields(it)
            }
        }

        reminderViewModel.let { vm ->
            vm.status.observe(viewLifecycleOwner) {
                if (!it)
                    findNavController().popBackStack()
            }
            vm.message.observe(viewLifecycleOwner) {
                if (!it.isNullOrBlank())
                    Snackbar
                        .make(
                            root,
                            it,
                            Snackbar.LENGTH_LONG
                        ).show()
            }
            vm.reminder.observe(viewLifecycleOwner) {
                if (it != null)
                    updateUI(it)
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabShowReminderDelete.setOnClickListener {
            reminderViewModel.delete()
        }
    }

    private fun updateUI(reminder: Reminder) {
        textViewShowReminderName.text = reminder.name
    }

    private fun showReminderFields(reminder: ReminderDetail) {
        textViewShowReminderName.text = reminder.name
    }
}