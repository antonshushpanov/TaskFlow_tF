package com.example.taskflow_tf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.taskflow_tf.databinding.ActivityMainBinding
import com.example.taskflow_tf.ui.home.HomeFragment
import com.example.taskflow_tf.ui.schedule.ScheduleFragment
import com.example.taskflow_tf.ui.calendar.CalendarFragment
import com.example.taskflow_tf.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Загружаем начальный фрагмент (Главная страница)
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
            // Устанавливаем первую доступную вкладку как выбранную
            binding.bottomNavigationView.selectedItemId = R.id.nav_schedule
        }

        // Настройка нижней панели навигации
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_schedule -> {
                    loadFragment(ScheduleFragment())
                    true
                }
                R.id.nav_calendar -> {
                    loadFragment(CalendarFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

