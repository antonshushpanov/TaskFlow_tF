package com.example.taskflow_tf.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskflow_tf.databinding.FragmentHomeBinding
import java.util.Calendar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Устанавливаем приветствие в зависимости от времени суток
        val greeting = getGreeting()
        binding.welcomeText.text = greeting
    }

    private fun getGreeting(): String {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        
        return when (hourOfDay) {
            in 5..11 -> "Доброе утро, Name!"
            in 12..16 -> "Добрый день, Name!"
            in 17..22 -> "Добрый вечер, Name!"
            else -> "Добро пожаловать, Name!"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

