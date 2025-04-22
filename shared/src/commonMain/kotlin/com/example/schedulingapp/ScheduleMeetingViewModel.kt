package com.example.schedulingapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScheduleMeetingViewModel: ScheduleMeetingViewModelInterface {
    private val _selectedTime = MutableStateFlow<String?>(null)
    override val selectedTime: StateFlow<String?> = _selectedTime.asStateFlow()

    override fun setSelectedTime(time: String) {
        _selectedTime.value = time
    }

    // Expose a suspend function to collect from StateFlow
    suspend fun collectSelectedTime(collector: (String?) -> Unit) {
        try {
            selectedTime.collect { value ->
                collector(value)  // Pass collected value to the collector (callback)
            }
        } catch (e: Exception) {
            // Handle any errors that might occur during collection
            throw e
        }
    }

}