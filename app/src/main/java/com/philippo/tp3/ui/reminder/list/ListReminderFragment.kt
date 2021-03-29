package com.philippo.tp3.ui.reminder.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.philippo.tp3.R
import com.philippo.tp3.adapter.RecyclerListReminder
import com.philippo.tp3.model.Reminder
import com.philippo.tp3.model.ReminderUtil
import kotlinx.android.synthetic.main.list_reminder_fragment.*

class ListReminderFragment : Fragment() {

    private lateinit var viewModel: ListReminderViewModel
    private lateinit var listReminderViewModelFactory: ListReminderViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val firebaseAuth = FirebaseAuth.getInstance()
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser == null)
//            findNavController().popBackStack()

//        val view = inflater.inflate(R.layout.list_reminder_fragment, container, false)

//        val listReminderViewModelFactory = ListReminderViewModelFactory(ReminderDaoFirestore())
//        viewModel = ViewModelProvider(this, listReminderViewModelFactory)
//            .get(ListReminderViewModel::class.java)

        val userId = arguments?.getLong("userId")

        listReminderViewModelFactory = ListReminderViewModelFactory(userId)

        viewModel = ViewModelProvider(
            this,
            listReminderViewModelFactory
        ).get(ListReminderViewModel::class.java)

        val view = inflater.inflate(R.layout.list_reminder_fragment, container, false)

        val listViewReminder: ListView = view.findViewById(R.id.listViewReminders)

        viewModel.reminders.observe(viewLifecycleOwner) {
            listViewReminder.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_expandable_list_item_1,
                it
            )
            listViewReminder.setOnItemClickListener { parent, view, position, id ->
                val bundle = bundleOf(
                    //Recebe um conjunto de chave e valor
                    "reminderId" to it[position].id
                )
                findNavController().navigate(
                    R.id.action_listReminderFragment_to_showReminderFragment2,
                    bundle
                )
            }
        }

        viewModel.message.observe(viewLifecycleOwner) {
            Log.i("ReminderFragment", it)
            if (it.isNotBlank())
                Snackbar.make(requireContext(), this.requireView(), it, Snackbar.LENGTH_LONG).show()
        }


//        viewModel.let { vm ->
//            vm.reminders.observe(viewLifecycleOwner) {
//                setupListViewReminders(it)
//            }

//            vm.message.observe(viewLifecycleOwner) {
//                Log.i("ListaApi", it)
//              if (it.isNotBlank())
//                Snackbar.make(requireContext(), this.requireView(), it, Snackbar.LENGTH_LONG).show()
//            }
//        }


//        viewModel.updateRemindersList()

//        viewModel.reminders.observe(viewLifecycleOwner) { reminders ->
//            val reminderRecyclerAdapter = RecyclerListReminder(reminders, this::actionClick)
//
//            //SWIPE DELETE
//            val itemTouchHelper = ItemTouchHelper(
//                object : ItemTouchHelper.SimpleCallback(
//                    0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//                ) {
//                    override fun onMove(
//                        recyclerView: RecyclerView,
//                        viewHolder: RecyclerView.ViewHolder,
//                        target: RecyclerView.ViewHolder
//                    ): Boolean {
//                        return false
//                    }
//
//                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                        val position = viewHolder.adapterPosition
//                        if (direction == ItemTouchHelper.LEFT) {
//                            val reminder = reminders[position]
//                            viewModel.delete()
//                            reminderRecyclerAdapter.notifyItemRemoved(position)
//                            showSnackbar(reminder)
//                        } else {
////                            val reminder = reminders[position]
////                            viewModel.deleteReminder(reminder)
//                            findNavController().navigate(R.id.formReminderFragment)
//                        }
//                    }
//                }
//            )
//
//            itemTouchHelper.attachToRecyclerView(listViewReminder.findViewById(R.id.listViewReminders))
//
////            recyclerListReminder.adapter = reminderRecyclerAdapter
////            recyclerListReminder.layoutManager = LinearLayoutManager(requireContext())
//
//            viewModel.reminders.observe(viewLifecycleOwner) {
//                listViewReminder.adapter = ArrayAdapter(
//                    requireContext(),
//                    android.R.layout.simple_expandable_list_item_1,
//                    it
//                )
//            }
//
//
////            listViewReminder = view.findViewById(R.id.recyclerListReminder)
//
//        }
        return view
    }


//    private fun actionClick(reminder: Reminder) {
//        ReminderUtil.reminderSelected = reminder
//        findNavController().navigate(R.id.showReminderFragment)
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //-----------------------------------------------------
        fabAddReminder.setOnClickListener {
            ReminderUtil.reminderSelected = null
            findNavController().navigate(R.id.formReminderFragment)
        }

        fabSignOut.setOnClickListener {
            viewModel.encerrarSessao()
            findNavController().navigate(R.id.loginFragment)
        }
    }

//    private fun setupListViewReminders(reminders: List<Reminder>) {
//        recyclerListReminder.adapter = RecyclerListReminder(reminders) {
//            ReminderUtil.reminderSelected = it
//            val bundle = bundleOf(
//                "reminderId" to it.id
//            )
//            findNavController().navigate(R.id.formReminderFragment)
//        }

//        recyclerListReminder.layoutManager = LinearLayoutManager(requireContext())


//    private fun showSnackbar(reminder: Reminder) {
//        Snackbar.make(
//            listViewReminder,
//            "${reminder.name} deleted 🥸",
//            Snackbar.LENGTH_LONG
//        ).show()
//    }
}
