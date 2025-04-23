package com.example.schedulingapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

interface AppointmentUseCase {
    fun getAvailableLocalDates(
        startDateTime: String,
        endDateTime: String,
        mockResponseName: String
    ): Flow<List<LocalDate>>

    fun getAvailableLocalTimes(
        date: LocalDate
    ): Flow<List<LocalTime>>

    fun formatDateTimeRange(startDateTime: LocalDateTime): String
}