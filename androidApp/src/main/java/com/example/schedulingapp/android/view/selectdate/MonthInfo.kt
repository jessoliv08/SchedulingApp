package com.example.schedulingapp.android.view.selectdate

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import java.time.YearMonth
import java.util.Locale

data class MonthInfo(
    val startDateTime: String,
    val endDateTime: String,
    val label: String
)


@RequiresApi(Build.VERSION_CODES.O)
fun getMonthInfo(yearMonth: YearMonth): MonthInfo {
    val zone = TimeZone.currentSystemDefault()

    val start = LocalDate(yearMonth.year, yearMonth.monthValue, 1)
        .atTime(LocalTime(hour = 0, minute = 0, second = 0))
        .toInstant(zone)
        .toString()

    val yearMonthEnd = yearMonth.atEndOfMonth()
    val endOfMonth = LocalDate(yearMonthEnd.year, yearMonthEnd.monthValue, yearMonthEnd.dayOfMonth)
        .atTime(LocalTime(hour = 23, minute = 59, second = 59))
        .toInstant(zone)
        .toString()

    val label = YearMonth.of(yearMonth.year, yearMonth.monthValue)
        .month.getDisplayName(java.time.format.TextStyle.SHORT, Locale.ENGLISH) + "${yearMonth.year}"

    return MonthInfo(start, endOfMonth, label)
}