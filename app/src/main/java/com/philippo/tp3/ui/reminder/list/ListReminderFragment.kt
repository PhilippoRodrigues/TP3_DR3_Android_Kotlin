package com.philippo.tp3.ui.reminder.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.philippo.tp3.R
import com.philippo.tp3.adapter.RecyclerListReminder
import com.philippo.tp3.dao.ReminderDaoFirestore
import com.philippo.tp3.model.Reminder
import com.philippo.tp3.model.ReminderUtil
import kotlinx.android.synthetic.main.list_reminder_fragment.*

class ListReminderFragment : Fragment() {

    private lateinit var viewModel: ListReminderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null)
            findNavController().popBackStack()

        val view = inflater.inflate(R.layout.list_reminder_fragment, container, false)

        val listReminderViewModelFactory = ListReminderViewModelFactory(ReminderDaoFirestore())
        viewModel = ViewModelProvider(this, listReminderViewModelFactory)
            .get(ListReminderViewModel::class.java)

        viewModel.reminders.observe(viewLifecycleOwner) {
            setupListViewReminders(it)
        }

        viewModel.updateRemindersList()

        viewModel.reminders.observe(viewLifecycleOwner) { reminders ->
            val reminderRecyclerAdapter = RecyclerListReminder(reminders, this::actionClick)

            val itemTouchHelper = ItemTouchHelper(
                object : ItemTouchHelper.SimpleCallback(
                    0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.adapterPosition
                        if (direction == ItemTouchHelper.LEFT) {
                            val reminder = reminders[position]
                            viewModel.deleteReminder(reminder)
                            reminderRecyclerAdapter.notifyItemRemoved(position)
                            showSnackbar(reminder)
                        } else {
//                            val reminder = reminders[position]
//                            viewModel.deleteReminder(reminder)
                            findNavController().navigate(R.id.formReminderFragment)
                        }
                    }
                }
            )

            itemTouchHelper.attachToRecyclerView(recyclerListReminder)

            recyclerListReminder.adapter = reminderRecyclerAdapter
            recyclerListReminder.layoutManager = LinearLayoutManager(requireContext())
        }

        return view
    }

    private fun actionClick(reminder: Reminder) {
        ReminderUtil.reminderSelected = reminder
        findNavController().navigate(R.id.showReminderFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAddReminder.setOnClickListener {
            ReminderUtil.reminderSelected = null
            findNavController().navigate(R.id.formReminderFragment)
        }

        fabSignOut.setOnClickListener {
            viewModel.encerrarSessao()
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun setupListViewReminders(reminders: List<Reminder>) {
        recyclerListReminder.adapter = RecyclerListReminder(reminders) {
            ReminderUtil.reminderSelected = it
            findNavController().navigate(R.id.formReminderFragment)
        }

        recyclerListReminder.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showSnackbar(reminder: Reminder) {
        Snackbar.make(
            recyclerListReminder,
            "${reminder.name} deleted 🥸",
            Snackbar.LENGTH_LONG
        ).show()
    }
}