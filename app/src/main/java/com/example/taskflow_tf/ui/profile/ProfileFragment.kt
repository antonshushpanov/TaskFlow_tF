package com.example.taskflow_tf.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskflow_tf.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProfile()
    }

    private fun setupProfile() {
        binding.saveButton.setOnClickListener {
            // Сохранение данных профиля
            saveProfileData()
        }

        binding.avatarImage.setOnClickListener {
            // Открытие выбора фото
            // TODO: Реализовать выбор фото
        }
    }

    private fun saveProfileData() {
        val name = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val phone = binding.phoneEditText.text.toString()
        val bio = binding.bioEditText.text.toString()

        // TODO: Сохранение данных в SharedPreferences или базу данных
        
        // Временно показываем сообщение
        android.widget.Toast.makeText(
            requireContext(),
            "Профиль сохранен",
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

