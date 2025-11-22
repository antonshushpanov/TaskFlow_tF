package com.example.taskflow_tf.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskflow_tf.databinding.ItemEventBinding
import com.example.taskflow_tf.model.Event

class EventAdapter(
    private val onEventClick: (Event) -> Unit
) : ListAdapter<Event, EventAdapter.EventViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(binding, onEventClick)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EventViewHolder(
        private val binding: ItemEventBinding,
        private val onEventClick: (Event) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.eventTitleText.text = event.title
            binding.eventTimeText.text = "${event.startTime} - ${event.endTime}"
            binding.eventCheckbox.isChecked = event.isCompleted

            binding.eventCheckbox.setOnCheckedChangeListener { _, isChecked ->
                // Обработка изменения состояния
            }

            binding.root.setOnClickListener {
                onEventClick(event)
            }

            binding.deleteButton.setOnClickListener {
                // Обработка удаления
            }
        }
    }
}

class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}

