// Calendar reference https://medium.com/@meytataliti/android-simple-calendar-with-jetpack-compose-v2-b7311bd6e331
package com.example.schedulingapp.android.view.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.LocalDate
import java.time.YearMonth

data class CalendarUiState(
    val yearMonth: YearMonth,
    val dates: List<Date>
) {
    companion object {
        val Init = CalendarUiState(
            yearMonth = YearMonth.now(),
            dates = emptyList()
        )
    }
    data class Date(
        val dayOfMonth: String,
        val isSelected: Boolean
    ) {
        companion object {
            val Empty = Date("", false)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun CalendarUiState.Date.toLocalDateOrNull(yearMonth: YearMonth): LocalDate? {
    return dayOfMonth.toIntOrNull()?.let { day ->
        try {
            LocalDate(yearMonth.year, yearMonth.monthValue, day)
        } catch (e: Exception) {
            null // Invalid day (e.g., "32")
        }
    }
}