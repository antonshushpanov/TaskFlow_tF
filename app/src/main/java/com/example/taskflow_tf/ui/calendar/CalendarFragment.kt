package com.example.taskflow_tf.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskflow_tf.databinding.FragmentCalendarBinding
import com.example.taskflow_tf.model.Event
import com.example.taskflow_tf.ui.adapter.EventAdapter
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private lateinit var eventAdapter: EventAdapter
    private var selectedDate: Calendar = Calendar.getInstance().apply {
        set(2025, 4, 23) // 23 мая 2025 (среда)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCalendarView()
        setupRecyclerView()
        updateSelectedDateDisplay()
    }

    private fun setupCalendarView() {
        updateMonthDisplay()
        
        binding.prevMonthButton.setOnClickListener {
            selectedDate.add(Calendar.MONTH, -1)
            updateMonthDisplay()
            updateSelectedDateDisplay()
        }
        
        binding.nextMonthButton.setOnClickListener {
            selectedDate.add(Calendar.MONTH, 1)
            updateMonthDisplay()
            updateSelectedDateDisplay()
        }
    }

    private fun updateMonthDisplay() {
        val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale("ru"))
        binding.monthYearText.text = monthYearFormat.format(selectedDate.time)
    }

    private fun setupRecyclerView() {
        eventAdapter = EventAdapter { event ->
            // Обработка клика на событие
        }
        binding.eventsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.eventsRecyclerView.adapter = eventAdapter
        loadEventsForSelectedDate()
    }

    private fun updateSelectedDateDisplay() {
        val dateFormat = SimpleDateFormat("dd.MM.yy EEEE", Locale("ru"))
        binding.selectedDateText.text = dateFormat.format(selectedDate.time)
        loadEventsForSelectedDate()
    }

    private fun loadEventsForSelectedDate() {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val selectedDateString = dateFormat.format(selectedDate.time)
        
        // Заглушка для тестовых данных
        val events = getSampleEventsForDate(selectedDateString)
        eventAdapter.submitList(events)
    }

    private fun getSampleEventsForDate(date: String): List<Event> {
        // Если выбрана дата 23.05.25 (среда), показываем события
        if (date.startsWith("23.05")) {
            return listOf(
                Event(
                    title = "Завтрак",
                    startTime = "08:00",
                    endTime = "08:15",
                    date = date
                ),
                Event(
                    title = "Собеседование",
                    startTime = "11:30",
                    endTime = "11:40",
                    date = date
                ),
                Event(
                    title = "Собеседование",
                    startTime = "11:30",
                    endTime = "11:40",
                    date = date
                )
            )
        }
        return emptyList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

