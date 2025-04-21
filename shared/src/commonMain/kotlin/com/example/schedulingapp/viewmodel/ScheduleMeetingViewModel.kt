package com.example.schedulingapp.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScheduleMeetingViewModel {
    private val _selectedTime = MutableStateFlow<String?>(null)
    val selectedTime: StateFlow<String?> = _selectedTime.asStateFlow()

    fun setSelectedTime(time: String) {
        _selectedTime.value = time
    }

    fun getSelectedTime(): String? {
        return _selectedTime.value
    }
}