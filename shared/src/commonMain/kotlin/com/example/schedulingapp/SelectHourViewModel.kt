package com.example.schedulingapp

import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

interface SelectHourViewModel {
    val timeZoneTitle: String
    val timeZone: IconText
    val dateSelected: StateFlow<LocalDate?>
    fun setSelectedDate(time: LocalDate)
    val selectHourTitle: String
    val selectHourDescription: String
    val availableTime: StateFlow<List<LocalTime>>
}