package com.philippo.api.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.philippo.api.R
import com.philippo.api.model.ReminderDetail
import org.w3c.dom.Text

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var dashboardViewModelFactory: DashboardViewModelFactory

    private lateinit var textViewReminderDetailName: TextView
    private lateinit var textViewReminderDetailDescription: TextView
    private lateinit var textViewReminderDetailOwner: TextView
    private lateinit var listViewReminderDetailMembersName: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val reminderId = arguments?.getLong("reminderId")

        if (reminderId == null)
            findNavController().popBackStack()

        dashboardViewModelFactory = DashboardViewModelFactory(reminderId!!)
        dashboardViewModel =
            ViewModelProvider(this, dashboardViewModelFactory).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        textViewReminderDetailName = root.findViewById(R.id.textViewReminderDetailName)
        textViewReminderDetailDescription = root.findViewById(R.id.textViewReminderDetailDescription)
        textViewReminderDetailOwner = root.findViewById(R.id.textViewReminderDetailOwner)
        listViewReminderDetailMembersName = root.findViewById(R.id.listViewReminderDetailMembersName)

        dashboardViewModel.reminder.observe(viewLifecycleOwner) {
            if (it != null)
                preencherInformacoesReminderDetail(it)
        }

        return root
    }

    private fun preencherInformacoesReminderDetail(reminderDetail: ReminderDetail) {
        textViewReminderDetailName.text = reminderDetail.name
        textViewReminderDetailDescription.text = reminderDetail.description
        textViewReminderDetailOwner.text = reminderDetail.owner?.full_name_display
        listViewReminderDetailMembersName.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_expandable_list_item_1,
            reminderDetail.members
        )

        listViewReminderDetailMembersName.setOnItemClickListener { parent, view, position, id ->
            val userId = reminderDetail.members[position].id
            val bundle = bundleOf("userId" to userId)
            findNavController().navigate(R.id.navigation_home, bundle)
        }
    }
}