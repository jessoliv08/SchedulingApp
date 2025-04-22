package com.example.schedulingapp

import kotlinx.coroutines.flow.StateFlow

interface ScheduleMeetingViewModelInterface {
    val selectedTime: StateFlow<String?>
    fun setSelectedTime(time: String)
}