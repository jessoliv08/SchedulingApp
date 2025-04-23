package com.example.schedulingapp

import com.example.schedulingapp.usecase.AppointmentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate

class SelectDateViewModelImpl(
    scope: CoroutineScope,
    useCase: AppointmentUseCase? = null
): SelectDateViewModel {
    override val logo: ImageResource = ImageResource.LOGO
    override val interviewerProfileImage: ImageResource = ImageResource.PROFILE_IMAGE
    override val interviewerName = "Jessica Oliveira"
    override val interviewerDetail = "30 Minute Interview"
    override val timeInfo = IconText(
        icon = ImageResource.CLOCK_ICON,
        text = "30 min"
    )
    override val callInfo = IconText(
        icon = ImageResource.MEETING_ICON,
        text = "Web conference details provided upon confirmation"
    )
    override val timeZoneTitle = "Time Zone"
    override val timeZone = IconText(
        icon = ImageResource.PLANET_ICON,
        text = "Eastern Time - US & Canada"
    )
    override val selectDateTitle = "Select a Day"
    private val _startDate = MutableStateFlow<String?>(null)
    private val startDate: StateFlow<String?> = _startDate.asStateFlow()
    private val _endDate = MutableStateFlow<String?>(null)
    private val endDate: StateFlow<String?> = _endDate.asStateFlow()
    private val _monthDate = MutableStateFlow<String?>(null)
    private val monthDate: StateFlow<String?> = _monthDate.asStateFlow()

    override fun setDateAndTime(date: String, endDate: String, month: String) {
        _startDate.value = date
        _endDate.value = endDate
        _monthDate.value = month
    }

    private val _availableDate: StateFlow<List<LocalDate>> =
        combine(startDate, endDate, monthDate) { startDate, endDate, monthDate ->
            if (startDate != null && endDate != null && monthDate != null) {
                useCase?.getAvailableLocalDates(
                    startDate, endDate, monthDate
                )?.last() ?: emptyList()
            } else emptyList()
        }.stateIn(scope, SharingStarted.Lazily, emptyList())
    override val availableDate: StateFlow<List<LocalDate>> = _availableDate
}