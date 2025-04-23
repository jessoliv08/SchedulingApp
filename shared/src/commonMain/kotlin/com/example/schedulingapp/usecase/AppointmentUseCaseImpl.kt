package com.example.schedulingapp.usecase

import com.example.schedulingapp.repository.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime


class AppointmentUseCaseImpl(
    private val repository: AppointmentRepository
): AppointmentUseCase {
    override fun getAvailableLocalDates(
        startDateTime: String,
        endDateTime: String,
        mockResponseName: String
    ): Flow<List<LocalDate>> =
        flow {
            emit(repository.getAvailableTimes(startDateTime, endDateTime, mockResponseName)
                .map { convertToLocalDate(it) }.distinct())
        }

    override fun getAvailableLocalTimes(
        date: LocalDate,
        mockResponseName: String
    ): Flow<List<LocalTime>> =
        flow {
            val startEnd = getStartAndEndDateStrings(date)
            emit(repository.getAvailableTimes(startEnd.first, startEnd.second, mockResponseName).map {
                convertToLocalDateTime(it)
            }.filter {
                it.date == date
            }.map { it.time }.distinct()
            )
        }

    override fun formatDateTimeRange(startDateTime: LocalDateTime): String {
        val endDateTime = startDateTime
            .toInstant(TimeZone.currentSystemDefault())
            .plus(30, DateTimeUnit.MINUTE, TimeZone.currentSystemDefault())
            .toLocalDateTime(TimeZone.currentSystemDefault())

        val startHour = formatTime(startDateTime)
        val endHour = formatTime(endDateTime)

        val dayOfWeek = startDateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        val month = startDateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }
        val day = startDateTime.dayOfMonth
        val year = startDateTime.year

        return "$startHour - $endHour, $dayOfWeek, $month $day, $year"
    }

    private fun formatTime(dateTime: LocalDateTime): String {
        val hour = dateTime.hour
        val minute = dateTime.minute
        val isAm = hour < 12
        val hour12 = if (hour % 12 == 0) 12 else hour % 12
        val minutePadded = minute.toString().padStart(2, '0')
        val suffix = if (isAm) "am" else "pm"
        return "$hour12:$minutePadded$suffix"
    }

    private fun getStartAndEndDateStrings(date: LocalDate): Pair<String, String> {

        val startDateTime = date.toFormattedDateTime(0, 0, 0) // 07:00:00 on the given date
        val endDateTime = date.toFormattedDateTime(23, 59, 59) // 23:59:59 is the valid max time in a day

        return Pair(startDateTime, endDateTime)
    }

    private fun convertToLocalDate(isoString: String): LocalDate {
        val instant = Instant.parse(isoString)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    private fun convertToLocalDateTime(isoString: String): LocalDateTime {
        val instant = Instant.parse(isoString)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault())
    }
}

fun LocalDate.toFormattedDateTime(hour: Int, minute: Int, second: Int): String {
    return "${this}T${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}"
}