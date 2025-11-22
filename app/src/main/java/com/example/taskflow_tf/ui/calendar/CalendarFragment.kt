package com.example.taskflow_tf.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskflow_tf.databinding.FragmentCalendarBinding
import com.example.taskflow_tf.model.Event
import com.example.taskflow_tf.ui.adapter.CalendarDateAdapter
import com.example.taskflow_tf.ui.adapter.CalendarDateItem
import com.example.taskflow_tf.ui.adapter.EventAdapter
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private lateinit var eventAdapter: EventAdapter
    private lateinit var calendarDateAdapter: CalendarDateAdapter
    private var selectedDate: Calendar = Calendar.getInstance().apply {
        set(2025, 4, 23) // 23 мая 2025 (среда)
    }
    private var currentMonth: Calendar = Calendar.getInstance().apply {
        time = selectedDate.time
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
        setupCalendarGrid()
        setupRecyclerView()
        updateSelectedDateDisplay()
    }

    private fun setupCalendarView() {
        updateMonthDisplay()
        
        binding.prevMonthButton.setOnClickListener {
            currentMonth.add(Calendar.MONTH, -1)
            updateMonthDisplay()
            updateCalendarGrid()
        }
        
        binding.nextMonthButton.setOnClickListener {
            currentMonth.add(Calendar.MONTH, 1)
            updateMonthDisplay()
            updateCalendarGrid()
        }
    }

    private fun updateMonthDisplay() {
        val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale("ru"))
        binding.monthYearText.text = monthYearFormat.format(currentMonth.time)
    }

    private fun setupCalendarGrid() {
        calendarDateAdapter = CalendarDateAdapter { calendar ->
            selectedDate.time = calendar.time
            updateSelectedDateDisplay()
            updateCalendarGrid()
        }
        binding.calendarGridRecyclerView.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.calendarGridRecyclerView.adapter = calendarDateAdapter
        updateCalendarGrid()
    }

    private fun updateCalendarGrid() {
        val calendarItems = mutableListOf<CalendarDateItem>()
        
        // Заголовки дней недели
        val dayNames = arrayOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
        for (day in dayNames) {
            calendarItems.add(CalendarDateItem(0, false, false, false, Calendar.getInstance()))
        }
        
        // Получаем первый день месяца и день недели
        val firstDay = Calendar.getInstance().apply {
            time = currentMonth.time
            set(Calendar.DAY_OF_MONTH, 1)
        }
        val firstDayOfWeek = (firstDay.get(Calendar.DAY_OF_WEEK) + 5) % 7 // Понедельник = 0
        
        // Пустые ячейки до первого дня
        for (i in 0 until firstDayOfWeek) {
            calendarItems.add(CalendarDateItem(0, false, false, false, Calendar.getInstance()))
        }
        
        // Дни месяца
        val daysInMonth = firstDay.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (day in 1..daysInMonth) {
            val dateCalendar = Calendar.getInstance().apply {
                time = currentMonth.time
                set(Calendar.DAY_OF_MONTH, day)
            }
            val dateString = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(dateCalendar.time)
            val hasEvents = dateString.startsWith("23.05") || dateString.startsWith("15.05") || 
                          dateString.startsWith("18.05") || dateString.startsWith("21.05") ||
                          dateString.startsWith("24.05") || dateString.startsWith("25.05") ||
                          dateString.startsWith("08.06") || dateString.startsWith("11.06") ||
                          dateString.startsWith("15.06") || dateString.startsWith("22.06")
            
            val isSelected = dateCalendar.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR) &&
                           dateCalendar.get(Calendar.MONTH) == selectedDate.get(Calendar.MONTH) &&
                           dateCalendar.get(Calendar.DAY_OF_MONTH) == selectedDate.get(Calendar.DAY_OF_MONTH)
            
            calendarItems.add(CalendarDateItem(day, true, isSelected, hasEvents, dateCalendar))
        }
        
        calendarDateAdapter.submitList(calendarItems)
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
        updateCalendarGrid()
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

