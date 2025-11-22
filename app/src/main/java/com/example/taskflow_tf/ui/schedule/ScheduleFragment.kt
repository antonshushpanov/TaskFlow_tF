package com.example.taskflow_tf.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskflow_tf.databinding.FragmentScheduleBinding
import com.example.taskflow_tf.model.Event
import com.example.taskflow_tf.ui.adapter.ScheduleAdapter
import com.example.taskflow_tf.ui.adapter.ScheduleItem
import java.text.SimpleDateFormat
import java.util.*

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadScheduleEvents()
    }

    private fun setupRecyclerView() {
        scheduleAdapter = ScheduleAdapter { event, position ->
            // Обработка клика на событие
        }
        binding.scheduleRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.scheduleRecyclerView.adapter = scheduleAdapter
    }

    private fun loadScheduleEvents() {
        // Заглушка для тестовых данных
        val scheduleItems = getSampleEvents()
        scheduleAdapter.submitList(scheduleItems)
    }

    private fun getSampleEvents(): List<ScheduleItem> {
        val scheduleItems = mutableListOf<ScheduleItem>()
        
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        // Вторник
        calendar.set(2025, 4, 13) // 13 мая 2025 (вторник)
        scheduleItems.add(ScheduleItem.DayHeader("Вторник"))
        scheduleItems.add(ScheduleItem.EventItem(
            Event(
                title = "Завтрак",
                startTime = "08:00",
                endTime = "08:15",
                date = dateFormat.format(calendar.time),
                isCompleted = true
            )
        ))
        scheduleItems.add(ScheduleItem.EventItem(
            Event(
                title = "Тренировка",
                startTime = "09:30",
                endTime = "11:00",
                date = dateFormat.format(calendar.time)
            )
        ))
        scheduleItems.add(ScheduleItem.EventItem(
            Event(
                title = "Бизнес-встреча",
                startTime = "16:00",
                endTime = "17:00",
                date = dateFormat.format(calendar.time)
            )
        ))

        // Среда
        calendar.set(2025, 4, 14) // 14 мая 2025 (среда)
        scheduleItems.add(ScheduleItem.DayHeader("Среда"))
        scheduleItems.add(ScheduleItem.EventItem(
            Event(
                title = "Завтрак",
                startTime = "08:00",
                endTime = "08:15",
                date = dateFormat.format(calendar.time)
            )
        ))
        scheduleItems.add(ScheduleItem.EventItem(
            Event(
                title = "Собеседование",
                startTime = "11:30",
                endTime = "11:40",
                date = dateFormat.format(calendar.time)
            )
        ))

        // Четверг
        calendar.set(2025, 4, 15) // 15 мая 2025 (четверг)
        scheduleItems.add(ScheduleItem.DayHeader("Четверг"))
        scheduleItems.add(ScheduleItem.EventItem(
            Event(
                title = "Завтрак",
                startTime = "08:00",
                endTime = "08:15",
                date = dateFormat.format(calendar.time)
            )
        ))

        return scheduleItems
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

