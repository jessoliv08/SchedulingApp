package com.example.schedulingapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


class SelectDateViewModelImpl: SelectDateViewModel {
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
    private val _availableDate = MutableStateFlow(
        listOf(
            "2025-04-23T14:00:00Z",
            "2025-04-23T15:00:00Z",
            "2025-04-22T16:00:00Z",
            "2025-04-21T17:00:00Z",
            "2025-04-22T18:00:00Z"
        ).map { convertToLocalDate(it) }.distinct()
    )
    override val availableDate: StateFlow<List<LocalDate>> = _availableDate.asStateFlow()


    private fun convertToLocalDate(isoString: String): LocalDate {
        val instant = Instant.parse(isoString)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    }
}