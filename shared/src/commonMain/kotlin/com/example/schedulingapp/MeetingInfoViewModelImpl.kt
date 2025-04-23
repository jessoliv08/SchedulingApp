package com.example.schedulingapp

import kotlinx.coroutines.flow.MutableStateFlow

class MeetingInfoViewModelImpl: MeetingInfoViewModel {
    override val logo: ImageResource = ImageResource.LOGO
    override val interviewInfo = "Meeting with Jessica Oliveira"
    override val timeInfo = IconText(
        icon = ImageResource.CLOCK_ICON,
        text = "30 min"
    )
    override val callInfo= IconText(
        icon = ImageResource.MEETING_ICON,
        text = "Web conference details provided upon confirmation"
    )
    override val timeZone = IconText(
        icon = ImageResource.PLANET_ICON,
        text = "Eastern Time - US & Canada"
    )

    override val cancelButton = object : ButtonViewModel {
        override fun action() {
            // clear all info
        }

        override val state = MutableStateFlow( ButtonViewModel.State(
            title = "Cancel Meeting",
            enable = true
        ))
    }
}