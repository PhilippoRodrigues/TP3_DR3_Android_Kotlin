package com.philippo.api.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.philippo.api.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val listViewReminder: ListView = root.findViewById(R.id.listViewReminders)
        homeViewModel.reminders.observe(viewLifecycleOwner) {
            listViewReminder.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_expandable_list_item_1,
                it
            )
        }

        homeViewModel.message.observe(viewLifecycleOwner) {
            Log.i("HomeFragment", it)
            if (it.isNotBlank())
                Snackbar.make(requireContext(), this.requireView(), it, Snackbar.LENGTH_LONG).show()
        }
        return root
    }
}