package com.example.taskflow_tf.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.taskflow_tf.databinding.ItemCalendarDateBinding
import java.util.Calendar

data class CalendarDateItem(
    val date: Int,
    val isCurrentMonth: Boolean,
    val isSelected: Boolean,
    val hasEvents: Boolean,
    val calendar: Calendar
)

class CalendarDateAdapter(
    private val onDateClick: (Calendar) -> Unit
) : ListAdapter<CalendarDateItem, CalendarDateAdapter.DateViewHolder>(DateDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val binding = ItemCalendarDateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DateViewHolder(binding, onDateClick)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DateViewHolder(
        private val binding: ItemCalendarDateBinding,
        private val onDateClick: (Calendar) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CalendarDateItem) {
            binding.dateText.text = if (item.date > 0) item.date.toString() else ""
            
            // Настройка видимости и цвета
            if (!item.isCurrentMonth) {
                binding.dateText.alpha = 0.3f
                binding.eventIndicator.visibility = View.GONE
            } else {
                binding.dateText.alpha = 1f
                binding.eventIndicator.visibility = if (item.hasEvents) View.VISIBLE else View.GONE
            }
            
            // Выделение выбранной даты
            if (item.isSelected) {
                binding.root.setBackgroundColor(0xFFE0E0E0.toInt())
            } else {
                binding.root.background = binding.root.context.getDrawable(android.R.drawable.list_selector_background)
            }
            
            binding.root.setOnClickListener {
                if (item.isCurrentMonth && item.date > 0) {
                    onDateClick(item.calendar)
                }
            }
        }
    }

    class DateDiffCallback : DiffUtil.ItemCallback<CalendarDateItem>() {
        override fun areItemsTheSame(oldItem: CalendarDateItem, newItem: CalendarDateItem): Boolean {
            return oldItem.date == newItem.date && 
                   oldItem.calendar.get(Calendar.YEAR) == newItem.calendar.get(Calendar.YEAR) &&
                   oldItem.calendar.get(Calendar.MONTH) == newItem.calendar.get(Calendar.MONTH) &&
                   oldItem.calendar.get(Calendar.DAY_OF_MONTH) == newItem.calendar.get(Calendar.DAY_OF_MONTH)
        }

        override fun areContentsTheSame(oldItem: CalendarDateItem, newItem: CalendarDateItem): Boolean {
            return oldItem == newItem
        }
    }
}

