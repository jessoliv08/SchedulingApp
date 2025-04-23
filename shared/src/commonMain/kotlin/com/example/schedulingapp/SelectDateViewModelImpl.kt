package com.example.schedulingapp

import com.example.schedulingapp.usecase.AppointmentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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
    override val callInfo= IconText(
        icon = ImageResource.MEETING_ICON,
        text = "Web conference details provided upon confirmation"
    )
    override val timeZoneTitle = "Time Zone"
    override val timeZone = IconText(
        icon = ImageResource.PLANET_ICON,
        text = "Eastern Time - US & Canada"
    )
    override val selectDateTitle = "Select a Day"
    private val _availableDate = useCase?.getAvailableLocalDates(
        "2025-04-01T07:00:00", "2025-04-30T06:59:59", "Apr2025"
    )?.stateIn(scope, SharingStarted.Lazily, emptyList()) ?: MutableStateFlow(emptyList())
    override val availableDate: StateFlow<List<LocalDate>> = _availableDate
}