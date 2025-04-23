package com.example.schedulingapp

import com.example.schedulingapp.usecase.AppointmentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import com.example.schedulingapp.usecase.MeetingUseCase
import kotlinx.coroutines.flow.map

class SetupInterviewedViewModelImpl(
    scope: CoroutineScope,
    private val meetingUseCase: MeetingUseCase? = null,
    private val appointmentUseCase: AppointmentUseCase? = null
): SetupInterviewedViewModel {
    override val logo: ImageResource = ImageResource.LOGO
    override val interviewInfo = "30 Minute Interview"
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
    override val enterDetails = "Enter Details"
    override val nameLabel = "Name *"
    override val nameField = MutableStateFlow("")
    override fun onNameChanged(newValue: String) {
        nameField.value = newValue
    }
    override val emailLabel = "Email *"
    override val emailField = MutableStateFlow("")
    override fun onEmailChanged(newValue: String) {
        emailField.value = newValue
    }
    override val descriptionAgree = "By proceeding, you confirm that you have read and agree to Calendly´s Terms of Use and Privacy Notice"
    private val _dateSelected = MutableStateFlow<LocalDateTime?>(null)
    private val dateSelected: StateFlow<LocalDateTime?> = _dateSelected.asStateFlow()

    override fun setDateAndTime(date: LocalDate, time: LocalTime) {
        _dateSelected.value = LocalDateTime(date.year, date.monthNumber, date.dayOfMonth, time.hour, time.minute, time.second)
    }

    override val selectDateAndTime = dateSelected.mapNotNull {
        it?.let { localDateAndTime ->
            IconText(
                icon = ImageResource.CALENDAR_ICON,
                text = appointmentUseCase?.formatDateTimeRange(localDateAndTime) ?: ""
            )
        }
    }.stateIn(scope, SharingStarted.Lazily, null)

    override val confirmButton = object : ButtonViewModel {
        override fun action() {
            // save date, hour, name and email
            if (nameField.value.isNotBlank() && emailField.value.isNotBlank() && dateSelected.value != null) {
                meetingUseCase?.saveMeetingDate(
                    nameField.value,
                    emailField.value,
                    appointmentUseCase?.formatDateTimeRange(dateSelected.value!!) ?: ""
                )
            }
        }

        override val state = nameField.combine(emailField) { name, email ->
            ButtonViewModel.State(
                enable = name.isNotBlank() && email.isNotBlank(),
                title = "Schedule Event"
            )
        }.stateIn(scope, SharingStarted.Lazily, null)
    }

    override val isMeetAvailable = meetingUseCase?.getMeetingDate()?.map {
        it != null
    }?.stateIn(scope, SharingStarted.Lazily, true) ?: MutableStateFlow(true)
}