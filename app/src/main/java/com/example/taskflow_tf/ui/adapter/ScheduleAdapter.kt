package com.example.taskflow_tf.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskflow_tf.databinding.ItemDayHeaderBinding
import com.example.taskflow_tf.databinding.ItemEventBinding
import com.example.taskflow_tf.model.Event

sealed class ScheduleItem {
    data class DayHeader(val dayName: String) : ScheduleItem()
    data class EventItem(val event: Event) : ScheduleItem()
}

class ScheduleAdapter(
    private val onEventClick: (Event, Int) -> Unit
) : ListAdapter<ScheduleItem, RecyclerView.ViewHolder>(ScheduleDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ScheduleItem.DayHeader -> TYPE_HEADER
            is ScheduleItem.EventItem -> TYPE_EVENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = ItemDayHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DayHeaderViewHolder(binding)
            }
            TYPE_EVENT -> {
                val binding = ItemEventBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                EventViewHolder(binding, onEventClick)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ScheduleItem.DayHeader -> {
                (holder as DayHeaderViewHolder).bind(item.dayName)
            }
            is ScheduleItem.EventItem -> {
                (holder as EventViewHolder).bind(item.event, position)
            }
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_EVENT = 1
    }
}

class DayHeaderViewHolder(
    private val binding: ItemDayHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(dayName: String) {
        binding.dayNameText.text = dayName
    }
}

class EventViewHolder(
    private val binding: ItemEventBinding,
    private val onEventClick: (Event, Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(event: Event, position: Int) {
        binding.eventTitleText.text = event.title
        binding.eventTimeText.text = "${event.startTime} - ${event.endTime}"
        binding.eventCheckbox.isChecked = event.isCompleted

        binding.eventCheckbox.setOnCheckedChangeListener { _, isChecked ->
            // Обработка изменения состояния
        }

        binding.root.setOnClickListener {
            onEventClick(event, position)
        }

        binding.deleteButton.setOnClickListener {
            // Обработка удаления
        }
    }
}

class ScheduleDiffCallback : DiffUtil.ItemCallback<ScheduleItem>() {
    override fun areItemsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean {
        return when {
            oldItem is ScheduleItem.DayHeader && newItem is ScheduleItem.DayHeader -> {
                oldItem.dayName == newItem.dayName
            }
            oldItem is ScheduleItem.EventItem && newItem is ScheduleItem.EventItem -> {
                oldItem.event.id == newItem.event.id
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean {
        return oldItem == newItem
    }
}
