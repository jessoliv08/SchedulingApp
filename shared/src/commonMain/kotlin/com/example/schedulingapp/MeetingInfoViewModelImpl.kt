package com.example.schedulingapp

import com.example.schedulingapp.usecase.MeetingUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn

class MeetingInfoViewModelImpl(
    scope: CoroutineScope,
    private val meetingUseCase: MeetingUseCase
): MeetingInfoViewModel {
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
            meetingUseCase.clearMeetingDate()
        }

        override val state = MutableStateFlow( ButtonViewModel.State(
            title = "Cancel Meeting",
            enable = true
        ))
    }

    private val meetingInfo = meetingUseCase.getMeetingDate()

    override val calendarInfo = meetingInfo.mapNotNull {
        IconText(
            ImageResource.CALENDAR_ICON,
            it?.dateTime ?: ""
        )
    }.stateIn(scope, SharingStarted.Lazily, null)

    override val name = meetingInfo.mapNotNull {
        IconText(
            ImageResource.PROFILE_IMAGE,
            it?.name ?: ""
        )
    }.stateIn(scope, SharingStarted.Lazily, null)

    override val email = meetingInfo.mapNotNull {
        IconText(
            ImageResource.EMAIL_ICON,
            it?.email ?: ""
        )
    }.stateIn(scope, SharingStarted.Lazily, null)
}