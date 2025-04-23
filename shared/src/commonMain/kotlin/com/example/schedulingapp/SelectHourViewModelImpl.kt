package com.example.schedulingapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class SelectHourViewModelImpl(scope: CoroutineScope): SelectHourViewModel {
    private val _dateSelected = MutableStateFlow<LocalDate?>(null)
    override val dateSelected: StateFlow<LocalDate?> = _dateSelected.asStateFlow()

    override fun setSelectedDate(time: LocalDate) {
        _dateSelected.value = time
    }

    override val timeZoneTitle = "Time Zone"
    override val timeZone = IconText(
        icon = ImageResource.PLANET_ICON,
        text = "Eastern Time - US & Canada"
    )
    override val selectHourTitle = "Select a Time"
    override val selectHourDescription = "Duration: 30 min"
    private val _availableDate = dateSelected.mapNotNull { date ->
        listOf(
            "2025-04-23T14:00:00Z",
            "2025-04-23T15:00:00Z",
            "2025-04-22T16:00:00Z",
            "2025-04-21T17:00:00Z",
            "2025-04-22T18:00:00Z"
        ).map {
            convertToLocalDate(it)
        }.filter {
            it.date == date
        }.map { it.time }.distinct()
    }.stateIn(scope, SharingStarted.Lazily, emptyList())
    override val availableTime: StateFlow<List<LocalTime>> = _availableDate

    private fun convertToLocalDate(isoString: String): LocalDateTime {
        val instant = Instant.parse(isoString)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault())
    }
}