package com.example.schedulingapp

import com.example.schedulingapp.usecase.AppointmentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

class SelectHourViewModelImpl(scope: CoroutineScope, useCase: AppointmentUseCase? = null): SelectHourViewModel {
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
    private val _availableDate = dateSelected.flatMapLatest { date ->
        date?.let {
            useCase?.getAvailableLocalTimes(it, "Apr2025")
        } ?: flowOf(emptyList())
    }.stateIn(scope, SharingStarted.Lazily, emptyList())
    override val availableTime: StateFlow<List<LocalTime>> = _availableDate
}