package com.philippo.tp3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.philippo.tp3.R
import com.philippo.tp3.model.Reminder
import kotlinx.android.synthetic.main.recycler_list_reminder.view.*

class RecyclerListReminder(
    private val reminders: List<Reminder>,
    private val actionClick: (Reminder) -> Unit
) : RecyclerView.Adapter<RecyclerListReminder.ReminderViewHolder>() {

    class ReminderViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
        val reminderName: TextView = viewItem.textViewRecyclerReminderText
//        val reminderType: TextView = viewItem.textViewRecyclerReminderType
//        val reminderText: TextView = viewItem.textViewRecyclerReminderText
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_list_reminder, parent, false)

        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder = reminders[position]
        holder.reminderName.text = reminder.name
//        holder.reminderType.text = reminder.type
//        holder.reminderText.text = reminder.content

        holder.itemView.setOnClickListener {
            actionClick(reminder)
        }
    }

    override fun getItemCount(): Int = reminders.size
}